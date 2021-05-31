package cn.stylefeng.guns.cloud.order.consumer;

import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("guns-cloud-member")
@RequestMapping("/api/remote/member")
public interface RemoteMemberService {

    /**
     * @description 查询会员默认地址信息，必须是默认地址，需重写
     * @params
     * @return
     */
    @GetMapping("/address/get")
    GunsMemAddressResult getMemAddress(@RequestParam Long memberId,@RequestParam Long addressId);
    //ResponseData select(@RequestParam Long memberId);
}
