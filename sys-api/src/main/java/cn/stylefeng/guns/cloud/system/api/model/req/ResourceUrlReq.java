package cn.stylefeng.guns.cloud.system.api.model.req;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.cloud.model.web.request.AbstractBaseRequest;
import lombok.Data;

/**
 * 获取资源通过url请求
 *
 * @author fengshuonan
 * @Date 2019/5/13 20:51
 */
@Data
public class ResourceUrlReq extends AbstractBaseRequest {

    private String url;

    @Override
    public String checkParam() {
        if (StrUtil.isEmpty(url)) {
            return "请求url为空！";
        }
        return null;
    }

}
