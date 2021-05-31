package cn.stylefeng.guns.cloud.model.exp;

import cn.stylefeng.guns.cloud.model.enums.exp.AuthExceptionEnum;
import lombok.Getter;

import java.util.Map;

/**
 * 认证失败异常
 *
 * @author fengshuonan
 * @Date 2019/4/19 21:38
 */
@Getter
public class SpsAuthException extends ServiceException {

    /**
     * 渲染到前端的参数
     */
    private final Map<String, Object> renderMap;

    public SpsAuthException(AuthExceptionEnum authExceptionEnum, Map<String, Object> renderMap) {
        super(authExceptionEnum);
        this.renderMap = renderMap;
    }

}
