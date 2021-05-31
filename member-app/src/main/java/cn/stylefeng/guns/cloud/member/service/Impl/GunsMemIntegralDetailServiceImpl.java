package cn.stylefeng.guns.cloud.member.service.Impl;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemIntegralDetail;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemIntegralDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemIntegralDetailResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemIntegralDetailMapper;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemMapper;
import cn.stylefeng.guns.cloud.member.service.GunsMemIntegralDetailService;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class GunsMemIntegralDetailServiceImpl  extends ServiceImpl<GunsMemIntegralDetailMapper, GunsMemIntegralDetail> implements GunsMemIntegralDetailService {

    @Autowired
    private GunsMemService gunsMemService;


    @Override
    public Page<GunsMemIntegralDetailResult> getSelectList(GunsMemIntegralDetailParam param) {
        GunsMemParamResult memParamResult = gunsMemService.getById(param.getMemberId());
        if (memParamResult==null){
            throw new ServiceException(400,"会员不存在");
        }

        Page<GunsMemIntegralDetailResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        Page<GunsMemIntegralDetailResult> result = page.setRecords(baseMapper.getSelectList(page,param));
        System.out.println(result);
        return result;

    }
}
