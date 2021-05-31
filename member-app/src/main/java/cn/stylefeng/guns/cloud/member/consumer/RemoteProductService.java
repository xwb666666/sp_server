package cn.stylefeng.guns.cloud.member.consumer;

import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "guns-cloud-product")
@RequestMapping(value = "/api/remote/product")
public interface RemoteProductService {
    @GetMapping("selectCollect")
    int selectCollect(@RequestParam Long memberId);

    @GetMapping("selectBrowse")
    int selectBrowse(@RequestParam Long memberId);

}
