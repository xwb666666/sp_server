package cn.stylefeng.guns.cloud.product.consumer;

import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("guns-cloud-member")
@RequestMapping("/api/remote/member")
public interface RemoteMemberService {

    @PostMapping("/get")
    GunsMemParamResult getMem(@RequestParam Long memberId);

}
