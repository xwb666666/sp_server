package cn.stylefeng.guns.cloud.member.controller.api;
import cn.stylefeng.guns.cloud.member.consumer.RemoteProductService;
import cn.stylefeng.guns.cloud.member.model.api.param.*;
import cn.stylefeng.guns.cloud.member.model.api.result.MemberResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/member")
@Api(tags = "app-会员登录")
public class ApiMemberController {

    @Autowired
    private GunsMemService service;

    @Autowired
    private RemoteProductService remoteProductService;



    /**
     * 会员登录
     * @param
     * @return
     */
    @PostMapping("login")
    @ApiOperation("会员登录")
    public ResponseData login(@RequestBody LoginParam param){

        MemberResult login = service.login(param);

        return ResponseData.success(login);
    }

    /**
     * 获取验证码
     * 参数：就是一个手机号码
     * @param para
     * @return
     */
    @PostMapping("verifycode")
    @ApiOperation("获取验证码")
    public ResponseData verifyCode(@RequestBody Map<String,String>para) {
        if (para.get("mobile") == null) {
            throw new ServiceException(500, "手机号不能为空");
        }
        String mobile=para.get("mobile").trim();
        service.verifyCode(mobile);
        return ResponseData.success();
    }

    /**
     * 1、手机号码  2、验证码
     * @param para
     * @return
     */
    @PostMapping("verifylogin")
    @ApiOperation("通过验证码登录")
    public ResponseData verifylogin(@RequestBody Map<String,String>para){
        if (para.get("code") == null) {
            throw new ServiceException(500, "验证码不能为空");
        }
        String code=para.get("code").trim();

        if(para.get("mobile")==null)
            throw new ServiceException(500,"手机号码不能为空");
        String mobile=para.get("mobile").trim();
        /**
         * 1、验证验证码是否正确
         * 2、通过手机号获取会员信息，返回
         */

        MemberResult memberResult = service.loginByMobile(mobile,code);
        return ResponseData.success(memberResult);
    }


    /**
     * 参数：会员id,新密码
     * @param para
     * @return
     */
    @PostMapping("forgetpwd")
    @ApiOperation("修改密码")
    public ResponseData updatePwd(@RequestBody Map<String,String>para){
        if (para.get("mobile")==null){
            throw new ServiceException(500, "手机号不能为空");
        }

        Boolean aBoolean = service.updatePwd(para.get("password"),para.get("mobile"),para.get("code"));
        if (aBoolean){
            return ResponseData.success();
        }else {
            return ResponseData.error("密码修改失败");
        }

    }

    @PostMapping("photo")
    @ApiOperation("修改头像")
    public ResponseData photo(@RequestBody Map< String, String>para){
        if (para==null||para.get("memberId")==null||para.get("photo")==null){
            throw new ServiceException(500,"用户id不能为空");
        }
        service.updatePhoto(Long.parseLong(para.get("memberId")),para.get("photo"));
        return ResponseData.success();

    }

    @PostMapping("nickName")
    @ApiOperation("修改昵称")
    public ResponseData nickName(@RequestBody Map< String,String>para){
        if (para==null||para.get("memberId")==null||para.get("nickName")==null){
            throw new ServiceException(500,"id或昵称不能为空");
        }
        service.updateNickName(Long.parseLong(para.get("memberId")) ,para.get("nickName"));
        return ResponseData.success();
    }

    @GetMapping("integralDetail")
    @ApiOperation("金豆明细")
    public ResponseData integralDetail(DetailParam param){
        if (param.getMemberId()==null){
            throw new ServiceException(500,"用户id不能为空");
        }
        return ResponseData.success(service.selectIntegral(param));
    }


    @GetMapping("AccountDetail")
    @ApiOperation("账户余额明细")
    public ResponseData AccountDetail(@Validated AccountDetailParam param){
        if (param.getMemberId()==null){
            throw new ServiceException(500,"用户id不能为空");
        }
        return ResponseData.success(service.selectAccount(param));
    }


    @PostMapping("updateLoginPwd")
    @ApiOperation("修改登录密码")
    public ResponseData updateLoginPwd(@RequestBody UpdateLoginPwdParam param){
        if (param.getOldPassword()==null||param.getNewPassword()==null){
            throw new ServiceException(500,"密码不能为空");
        }

        service.updateLoginPwd(param);
        return ResponseData.success();
    }

    @PostMapping("updatePayPwd")
    @ApiOperation("修改支付密码")
    public ResponseData updatePayPwd(@RequestBody UpdatePayPwdParam param){
        if (param.getOldPayPwd()==null||param.getNewPayPwd()==null){
            throw new ServiceException(500,"密码不能为空");
        }

        service.updatePayPwd(param);
        return ResponseData.success();
    }


    @PostMapping("referrer")
    @ApiOperation("绑定推荐人")
    public ResponseData referrer(@RequestBody ReferrerParam param){

        service.referrer(param);

        return ResponseData.success();
    }

    @GetMapping("memberInfo")
    @ApiOperation("查询会员信息")
    public ResponseData memberInfo(Long memberId){

        MemberResult memberInfo = service.memberInfo(memberId);

        return ResponseData.success(memberInfo);
    }

}
