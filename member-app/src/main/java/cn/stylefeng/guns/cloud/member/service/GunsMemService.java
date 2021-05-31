package cn.stylefeng.guns.cloud.member.service;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemParam;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemberQueryParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.member.model.api.param.*;
import cn.stylefeng.guns.cloud.member.model.api.result.MemberResult;
import cn.stylefeng.guns.cloud.member.model.param.GunsMemberParam;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemSelectResult;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface GunsMemService extends IService<GunsMember> {

    void save(GunsMemberParam param);

    Page<GunsMemSelectResult> getPageList(GunsMemberQueryParam param);

    Boolean remove(Long id);

    Boolean update(GunsMemParam param);

    GunsMemParamResult getById(Long id);

    MemberResult login(LoginParam param);

    MemberResult loginByMobile(String mobile,String code);

    Boolean updatePhoto(Long memberId, String photo);

    Map<String,Object> selectIntegral(DetailParam param);

    Boolean updateNickName(Long memberId, String nickName);

    Map<String,Object> selectAccount(AccountDetailParam param);

    Boolean verifyCode(String mobile);

    Boolean updatePwd(String password,String mobile,String code);

    Boolean updateLoginPwd(UpdateLoginPwdParam param);

    Boolean updatePayPwd(UpdatePayPwdParam param);

    Boolean referrer(ReferrerParam param);

    public MemberResult memberInfo(Long memberId);

    GunsMemParamResult selectMember(Long memberId);
}
