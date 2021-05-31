package cn.stylefeng.guns.cloud.product.service.Impl;
import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.product.mapper.CollectMapper;
import cn.stylefeng.guns.cloud.product.model.api.param.AddCollectParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CollectResult;
import cn.stylefeng.guns.cloud.product.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;



@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, GunsProCollect> implements CollectService {


    @Override
    public Page<CollectResult> selectList(CollectParam param) {

        Page<CollectResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        Page<CollectResult> result = page.setRecords(baseMapper.selectList(page, param));

        return result;
    }

    @Override
    public Boolean addSave(GunsProCollect gunsProCollect) {
        QueryWrapper<GunsProCollect>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",gunsProCollect.getProductId()).eq("member_id",gunsProCollect.getMemberId());
        GunsProCollect collect = baseMapper.selectOne(queryWrapper);
        if (collect!=null){
            throw new ServiceException(500,"此收藏已存在，不能重复添加");
        }
        gunsProCollect.setCreateTime(new DateTime());
        baseMapper.insert(gunsProCollect);
        return true;
    }

    @Override
    public Boolean removeId(Long id) {
        if (id==null){
            throw new ServiceException(500,"会员id不能为空");
        }

        QueryWrapper<GunsProCollect>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        GunsProCollect gunsProCollect = baseMapper.selectOne(queryWrapper);
        if (gunsProCollect==null){
            throw new ServiceException(500,"此收藏已经删除");
        }
        baseMapper.delete(queryWrapper);
        return true;
    }
}
