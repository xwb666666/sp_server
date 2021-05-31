package cn.stylefeng.guns.cloud.member.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-26 18:42
 **/
@FeignClient(name = "guns-cloud-system")
@RequestMapping("/authService")
public interface SmsSenderService {
    @GetMapping("/sendCode")
    boolean sendCode(@RequestParam String mobile, @RequestParam String templateId, @RequestParam String code);
}
