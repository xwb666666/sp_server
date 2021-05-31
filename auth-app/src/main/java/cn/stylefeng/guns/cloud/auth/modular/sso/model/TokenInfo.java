package cn.stylefeng.guns.cloud.auth.modular.sso.model;

import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.auth.modular.sso.entity.AuthClient;
import lombok.Data;

/**
 * token信息
 *
 * @author fengshuonan
 * @Date 2019/9/25 21:51
 */
@Data
public class TokenInfo {
    private String ticket;
    private String token;
    private LoginUser loginUser;
    private AuthClient authClient;
}
