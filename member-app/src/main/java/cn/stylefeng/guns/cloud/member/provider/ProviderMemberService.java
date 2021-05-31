package cn.stylefeng.guns.cloud.member.provider;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAddress;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.service.GunsMemAddressService;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/remote/member")
public class ProviderMemberService {

    @Autowired
    private GunsMemAddressService addressService;

    @Autowired
    private GunsMemService memService;

    @PostMapping("/get")
    public GunsMemParamResult getMem(@RequestParam Long memberId){
        if (memberId == null) {
            throw new ServiceException(500, "会员id不能为空");
        }
        GunsMemParamResult member = memService.getById(memberId);
        return member;
    }

    //查询会员默认地址信息
    @GetMapping("/address/get")
    public GunsMemAddressResult getMemAddress(@RequestParam Long memberId,@RequestParam Long addressId) {
        if (memberId == null) {
            throw new ServiceException(500, "会员id不能为空");
        }
        GunsMemAddress gma = addressService.getOne(new QueryWrapper<GunsMemAddress>()
                .eq("member_id", memberId).eq("id",addressId)
                .eq("is_default", 0));
        GunsMemAddressResult gmzs = new GunsMemAddressResult();
        ToolUtil.copyProperties(gma, gmzs);
        return gmzs;
    }

    //查询会员账户，金豆，劵
    @PostMapping("/mem/address/{id}")
    public Object getMemAccount(@PathVariable(value = "id") Long id){
        return null;
    }
}
