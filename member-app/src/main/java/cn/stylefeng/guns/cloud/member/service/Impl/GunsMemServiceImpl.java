package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.log.Log;
import cn.stylefeng.guns.cloud.api.member.entity.*;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemParam;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemberQueryParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.api.util.ValidatorUtil;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.constants.Constants;
import cn.stylefeng.guns.cloud.member.consumer.SmsSenderService;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemAccountDetailMapper;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemAccountMapper;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemIntegralDetailMapper;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemMapper;
import cn.stylefeng.guns.cloud.member.model.GunsTask;
import cn.stylefeng.guns.cloud.member.model.GunsTaskMember;
import cn.stylefeng.guns.cloud.member.model.api.param.*;
import cn.stylefeng.guns.cloud.member.model.api.result.AccountDetailResult;
import cn.stylefeng.guns.cloud.member.model.api.result.IntegralDetailResult;
import cn.stylefeng.guns.cloud.member.model.api.result.MemberResult;
import cn.stylefeng.guns.cloud.member.model.param.GunsMemberParam;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemSelectResult;
import cn.stylefeng.guns.cloud.member.service.*;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GunsMemServiceImpl extends ServiceImpl<GunsMemMapper, GunsMember> implements GunsMemService {


    @Autowired
    private GunsMemAccountService accountService;


    @Autowired
    private GunsMemRanksService ranksService;

    @Autowired
    private GunsMemIntegralDetailService gunsMemIntegralDetailService;

    @Autowired
    private GunsMemAccountDetailService gunsMemAccountDetailService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    String keyfix = "verify_code:";

    @Autowired
    private SmsSenderService  smsSenderService;

    @Autowired
    private GunsTaskService gunsTaskService;

    @Autowired
    private GunsTaskMemberService gunsTaskMemberService;

    @Override
    @Transactional
    public void save(GunsMemberParam param) {

        /**
         * 1、添加会员基本信息
         *     判断用户是否重复
         *     判断手机号是否重复
         * 2、查询会员基本，查看初始积分
         * 3、初始化账号信息
         */

        GunsMember gunsMember = new GunsMember();
        GunsMember member = baseMapper.selectOne(new QueryWrapper<GunsMember>().eq("login_name", param.getLoginName()));

        if (member != null)
            throw new NullArgumentException("已经存在该用户名");
        GunsMember member_t2 = baseMapper.selectOne(new QueryWrapper<GunsMember>().eq("mobile", param.getMobile()));
        if (member_t2 != null)
            throw new NullArgumentException("手机号码已经存在");
        ToolUtil.copyProperties(param, gunsMember);
        gunsMember.setCreateTime(new DateTime());
        //密码加密
        if (param.getPassword() != null)
            gunsMember.setPassword(ToolUtil.md5Encrypt(param.getPassword(), ToolUtil.SALT));//添加密码加密
        //设置支付密码
        if (param.getPayPwd() != null && !param.getPayPwd().isEmpty())
            gunsMember.setPayPwd(ToolUtil.md5Encrypt(param.getPayPwd(),ToolUtil.SALT));//添加支付密码加密
        baseMapper.insert(gunsMember);
        //查询会员等级，查看等级默认积分，并赋值
        GunsMemRanks rank = ranksService.getOne(new QueryWrapper<GunsMemRanks>().eq("id", param.getRankId()));
        Double initIntegral = 0d; //初始积分
        if (rank != null) {
            initIntegral = rank.getStartScore();
        } else
            throw new ServiceException(500, "会员等级错误");
        //初始化账户信息
        GunsMemAccount account = new GunsMemAccount();
        account.setMemberId(gunsMember.getId());
        account.setIntegral(initIntegral);
        account.setLockMoney(0d);
        account.setMemberMoney(0d);
        accountService.save(account);
    }

    @Override
    public Page<GunsMemSelectResult> getPageList(GunsMemberQueryParam param) {
        Page<GunsMemSelectResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        Page<GunsMemSelectResult> result = page.setRecords(baseMapper.getPageList(page, param));
        return result;

    }


    @Override
    @Transactional
    public Boolean update(GunsMemParam param) {

        GunsMember member = baseMapper.selectOne(new QueryWrapper<GunsMember>().eq("id", param.getId()));

        if (member == null) {
            throw new ServiceException(500, "会员不存在");
        }

        if (!ValidatorUtil.isMobile(param.getMobile())) {
            throw new ServiceException(500, "手机号格式不正确");
        }

        QueryWrapper<GunsMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", param.getMobile()).ne("id", param.getId());
        Integer count = baseMapper.selectCount(queryWrapper);


        if (count > 0) {
            //通过手机号码去查下，看有没有已经使用该手机注册的会员，如果有抛异常
            throw new ServiceException(500, "手机号已被注册");

        }

        UpdateWrapper<GunsMember> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", param.getId());
        member.setNickName(param.getNickName());
        member.setRankId(param.getRankId());
        member.setStatus(param.getStatus());
        member.setPhoto(param.getPhoto());
        member.setGender(param.getGender());

        if (param.getPassword() != null && param.getPassword() != "") {
            member.setPassword(ToolUtil.md5Encrypt(param.getPassword(), ToolUtil.SALT));
        }

        member.setType(param.getType());
        member.setMobile(param.getMobile());
        if (param.getPayPwd() != null && param.getPayPwd() != "") {
            member.setPayPwd(ToolUtil.md5Encrypt(param.getPayPwd(), ToolUtil.SALT));
        }
        return baseMapper.update(member, wrapper) > 0;
    }

    @Override
    public GunsMemParamResult getById(Long id) {

        GunsMember member = baseMapper.selectById(id);
        if (member == null) throw new ServiceException(500, "没查到会员信息信息");

        GunsMemAccount memAccount = accountService.getOne(new QueryWrapper<GunsMemAccount>().eq("member_id", id));
        if (memAccount == null) throw new ServiceException(500, "没查到账户信息");
        GunsMemParamResult result = new GunsMemParamResult();
        result.setId(member.getId());
        result.setRankId(member.getRankId());
        result.setType(member.getType());
        result.setGender(member.getGender());
        result.setNickName(member.getNickName());
        result.setPhoto(member.getPhoto());
        result.setMobile(member.getMobile());
        result.setStatus(member.getStatus());
        result.setIntegral(memAccount.getIntegral());
        result.setLockMoney(memAccount.getLockMoney());
        result.setMemberMoney(memAccount.getMemberMoney());
        return result;
    }

    private MemberResult getResult(GunsMember member){


        MemberResult memberResult = new MemberResult();
        //获取会员信息
        GunsMember result = baseMapper.selectById(member.getId());
        memberResult.setPhoto(result.getPhoto());
        memberResult.setNickName(result.getNickName());
        memberResult.setId(result.getId());
        memberResult.setType(Long.valueOf(result.getType()));

        //获取等级信息
        GunsMemRanks ranks = ranksService.getById(member.getRankId());
        memberResult.setRankName(ranks.getName());
        memberResult.setRankId(ranks.getId());

        //获取账户信息
        QueryWrapper<GunsMemAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", member.getId());
        GunsMemAccount gunsMemAccount = accountService.getOne(wrapper);
        memberResult.setMemberMoney(gunsMemAccount.getMemberMoney());
        memberResult.setIntegral(gunsMemAccount.getIntegral());

        return memberResult;

    }

    @Override
    public MemberResult login(LoginParam param) {
        MemberResult memberResult = new MemberResult();
        QueryWrapper<GunsMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", param.getLoginName());
        GunsMember gunsMember = baseMapper.selectOne(queryWrapper);
        if (gunsMember == null) {
            throw new ServiceException(500, "登录账户不正确");
        }

        if (!gunsMember.getPassword().equals(ToolUtil.md5Encrypt(param.getPassword(),ToolUtil.SALT)))
           throw new ServiceException(500, "登录密码错误");

        if (gunsMember.getStatus() == 0) {
            throw new ServiceException(500, "账号已停用");
        }
        memberResult= getResult(gunsMember);
        return memberResult;
    }

    /**
     * 通过手机号码获取会员信息
     *
     * @param mobile
     * @return
     */
    @Override
    public MemberResult loginByMobile(String mobile, String code) {
        //验证验证码
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        String value = forValue.get(keyfix+mobile);
        if (StringUtils.isEmpty(value)) {
            throw new ServiceException(500, "验证码已过期");
        }
        if(!code.equals(value))
            throw new ServiceException(500, "验证码不正确");

        MemberResult memberResult = new MemberResult();
        QueryWrapper<GunsMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        GunsMember gunsMember = baseMapper.selectOne(queryWrapper);
        //删除缓存内的验证码
        redisTemplate.delete(keyfix+mobile);
        memberResult= getResult(gunsMember);
        return memberResult;
    }

    @Override
    public Boolean updatePhoto(Long memberId, String photo) {
        GunsMember gunsMember = baseMapper.selectById(memberId);
        if (gunsMember == null) {
            throw new ServiceException(500, "此id不存在");
        }
        gunsMember.setPhoto(photo);
        baseMapper.update(gunsMember, new UpdateWrapper<GunsMember>().set("photo", photo).eq("id", memberId));
        return true;
    }

    @Override
    public Boolean updateNickName(Long memberId, String nickName) {
        GunsMember gunsMember = baseMapper.selectById(memberId);
        if (gunsMember == null) {
            throw new ServiceException(500, "此id不存在");
        }
        gunsMember.setNickName(nickName);
        baseMapper.updateById(gunsMember);
        return true;
    }


    @Override
    public Map<String, Object> selectAccount(AccountDetailParam param) {

        Map<String, Object> result = new HashMap<>();
        GunsMemAccount account = accountService.getOne(new QueryWrapper<GunsMemAccount>().eq("member_id", param.getMemberId()));
        if (account == null) {
            throw new ServiceException(500, "未查到此用户账户信息");
        }
        result.put("member_money", account.getMemberMoney());

        QueryWrapper<GunsMemAccountDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", param.getMemberId()).between("operation_type", 1l, 2l);
        //操作类型 1充值 2提现
        if (param.getOperationType() != null && param.getOperationType() > 0) {
            queryWrapper.eq("operation_type", param.getOperationType());
        }


        Page<GunsMemAccountDetail> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        page = gunsMemAccountDetailService.page(page, queryWrapper);

        List<AccountDetailResult> list = page.getRecords().stream().map(detail -> {
            AccountDetailResult accountDetailResult = new AccountDetailResult();
            ToolUtil.copyProperties(detail, accountDetailResult);
            return accountDetailResult;
        }).collect(Collectors.toList());


        // page -> pageResult
        PageResult<AccountDetailResult> pageResult = new PageResult<>();
        pageResult.setPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getPages());
        pageResult.setTotalRows(page.getTotal());
        pageResult.setRows(list);

        result.put("list", pageResult);

        return result;

    }

    @Override
    public Boolean verifyCode(String mobile) {
        //首先从redis
        //生成验证码
        Double n_code= (Math.random() * 9 + 1) * 100000;
        String code = String.valueOf(n_code.intValue());
        //放入 redis中
        redisTemplate.opsForValue().set(keyfix+mobile, code, 10, TimeUnit.MINUTES);

        smsSenderService.sendCode(mobile,"937950",code);

        return true;
    }


    @Override
    public Boolean updatePwd(String password, String mobile,String code) {

        GunsMember member = baseMapper.selectOne(new QueryWrapper<GunsMember>().eq("mobile", mobile));
        if (member == null) {
            throw new ServiceException(500, "手机号不存在");
        }

        //验证验证码
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        String value = forValue.get(keyfix+mobile);
        if (StringUtils.isEmpty(value)) {
            throw new ServiceException(500, "验证码已过期");
        }
        if(!code.equals(value))
            throw new ServiceException(500, "验证码不正确");

        member.setPassword(ToolUtil.md5Encrypt(password,ToolUtil.SALT)); //要加密
        baseMapper.updateById(member);
        //删除缓存内的验证码
        redisTemplate.delete(keyfix+mobile);

        return true;
    }

    @Override
    public Boolean updateLoginPwd(UpdateLoginPwdParam param) {
        QueryWrapper<GunsMember>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",param.getMemberId());
        GunsMember gunsMember = baseMapper.selectOne(queryWrapper);
        if (!ToolUtil.md5Encrypt(param.getOldPassword(),ToolUtil.SALT).equals(gunsMember.getPassword())){
            throw new ServiceException(500,"原密码错误");
        }
        gunsMember.setPassword(ToolUtil.md5Encrypt(param.getNewPassword(),ToolUtil.SALT));//修改密码 加密
        baseMapper.update(gunsMember,queryWrapper);
        return true;
    }

    @Override
    public Boolean updatePayPwd(UpdatePayPwdParam param) {
        QueryWrapper<GunsMember>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",param.getMemberId());
        GunsMember gunsMember = baseMapper.selectOne(queryWrapper);
        if (!ToolUtil.md5Encrypt(param.getOldPayPwd(),ToolUtil.SALT).equals(gunsMember.getPayPwd())){
            throw new ServiceException(500,"原支付密码错误");
        }
        gunsMember.setPayPwd(ToolUtil.md5Encrypt(param.getNewPayPwd(),ToolUtil.SALT));//修改密码 加密
        baseMapper.update(gunsMember,queryWrapper);

        return true;
    }




    @Override
    public Map<String, Object> selectIntegral(DetailParam param) {
        Map<String, Object> result = new HashMap<>();
        GunsMemAccount account = accountService.getOne(new QueryWrapper<GunsMemAccount>().eq("member_id", param.getMemberId()));
        if (account == null) {
            throw new ServiceException(500, "未查到此用户金豆信息");
        }
        result.put("integral", account.getIntegral());

        QueryWrapper<GunsMemIntegralDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", param.getMemberId());

        Page<GunsMemIntegralDetail> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        page = gunsMemIntegralDetailService.page(page, queryWrapper);

        List<IntegralDetailResult> list = page.getRecords().stream().map(detail -> {
            IntegralDetailResult integralDetailResult = new IntegralDetailResult();
            ToolUtil.copyProperties(detail, integralDetailResult);
            return integralDetailResult;
        }).collect(Collectors.toList());

        // page -> pageResult
        PageResult<IntegralDetailResult> pageResult = new PageResult<>();
        pageResult.setPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getPages());
        pageResult.setTotalRows(page.getTotal());
        pageResult.setRows(list);

        result.put("list", pageResult);

        return result;
    }



    /**
     * 判断数据库中是否有此会员
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Boolean remove(Long id) {
        GunsMember member = baseMapper.selectById(id);
        GunsMemAccount account = accountService.findSelect(id);
        if (member == null && account == null) {

            throw new ServiceException(500, "没有此会员");
        }
        QueryWrapper<GunsMember> queryWrapper = new QueryWrapper<>();
        Integer count = baseMapper.selectCount(queryWrapper.eq("id", id));

        if (count > 0) {
            this.removeById(id);
            this.accountService.removeById(account.getId());
        }

        return count > 0;
    }

    @Override
    @Transactional
    public Boolean referrer(ReferrerParam param) {
        QueryWrapper<GunsMember>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("referral_code",param.getReferrer());
        GunsMember gunsMember = baseMapper.selectOne(queryWrapper);

        GunsMember member=baseMapper.selectById(param.getMemberId());
        if (gunsMember==null){
            throw new ServiceException(500,"推荐码不存在");
        }

        if (gunsMember.getId().equals(param.getMemberId())){

            throw new ServiceException(500,"不能绑定自己推荐码");
        }

        if (member.getReferrer()!=null){

            throw new ServiceException(500,"不能重复绑定");
        }

        member.setReferrer(param.getReferrer());
        baseMapper.updateById(member);

        QueryWrapper<GunsMemAccount>wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",param.getMemberId());

        GunsMemAccount accountIntegral = accountService.getOne(wrapper);    //获取当前金豆
        Double account = accountIntegral.getIntegral()+10d;
        UpdateWrapper<GunsMemAccount>updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("member_id",param.getMemberId()).set("integral",account);
        accountService.update(updateWrapper);

        //完成一条推荐人任务
        GunsTask gunsTask = gunsTaskService.getBaseMapper().selectOne(new QueryWrapper<GunsTask>().eq("task_type", Constants.TaskType.TASK_TYPE_1.getValue()));
        //"1:绑定推荐人 2:邀请好友 3:签到 4:看视频 5:视频任务 6:广告任务"
        GunsTaskMember gunsTaskMember = new GunsTaskMember();
        gunsTaskMember.setTaskId(gunsTask.getId());
        gunsTaskMember.setMemberId(param.getMemberId());
        int saveTaskMemResult = gunsTaskMemberService.saveOne(gunsTaskMember);
        if(saveTaskMemResult <= 0)
            throw new ServiceException(500, "添加任务完成失败！");

        GunsMemIntegralDetail integralDetail = new GunsMemIntegralDetail();
        integralDetail.setMemberId(param.getMemberId());
        integralDetail.setChangeFer(Double.valueOf(gunsTask.getReward()));
        integralDetail.setOperationType(gunsTask.getTaskType());
        integralDetail.setCurrFer(account);       //当前金豆
        integralDetail.setFerChange(Constants.FER_CHANGE_ADD);
        integralDetail.setRemark(Constants.TaskType.TASK_TYPE_3.getName());
        integralDetail.setFerUser(member.getLoginName());
        integralDetail.setCreateTime(new DateTime());
        gunsMemIntegralDetailService.save(integralDetail);

        return true;
    }
    @Override
    public MemberResult memberInfo(Long memberId) {
        MemberResult memberResult=new MemberResult();
        QueryWrapper<GunsMember> queryWrapper=new QueryWrapper();
        queryWrapper.eq("id", memberId);
        GunsMember gunsMember = baseMapper.selectOne(queryWrapper);
        if (gunsMember==null){
            throw new ServiceException(500,"会员信息不存在");
        }
        memberResult= getResult(gunsMember);
        return memberResult;
    }

    @Override
    public GunsMemParamResult selectMember(Long memberId) {
        if (memberId==null){
            throw new ServiceException(500,"用户id不能为空");
        }
        GunsMember gunsMember = baseMapper.selectById(memberId);
        GunsMemParamResult gunsMemParamResult=new GunsMemParamResult();
        ToolUtil.copyProperties(gunsMember,gunsMemParamResult);
        return gunsMemParamResult;
    }

}
