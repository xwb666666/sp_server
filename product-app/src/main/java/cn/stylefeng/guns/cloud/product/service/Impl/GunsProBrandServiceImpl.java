package cn.stylefeng.guns.cloud.product.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProBrand;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProProduct;
import cn.stylefeng.guns.cloud.api.product.model.params.PageBrandParam;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProBrandResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.product.mapper.GunsProBrandMapper;
import cn.stylefeng.guns.cloud.product.model.api.result.BrandResult;
import cn.stylefeng.guns.cloud.product.service.GunsProBrandService;
import cn.stylefeng.guns.cloud.product.service.GunsProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Service
public class GunsProBrandServiceImpl extends ServiceImpl<GunsProBrandMapper, GunsProBrand> implements GunsProBrandService {

    @Autowired
    private GunsProductService productService;



    @Override
    public Boolean update(GunsProBrand gunsProBrand) {
        GunsProBrand brand = baseMapper.selectOne(new QueryWrapper<GunsProBrand>().eq("id", gunsProBrand.getId()));
        if (brand==null){
            throw new ServiceException(500,"品牌信息不存在");
        }
        baseMapper.updateById(gunsProBrand);
        return true;
    }

    @Override
    public GunsProBrandResult findBySpec(Long id) {
        GunsProBrand gunsProBrand = baseMapper.selectById(id);
        if (gunsProBrand==null){
            throw new ServiceException(500,"此id不存在");
        }
        GunsProBrandResult gunsProBrandResult=new GunsProBrandResult();
        ToolUtil.copyProperties(gunsProBrand,gunsProBrandResult);

        return gunsProBrandResult;
    }




    @Override
    public PageResult<GunsProBrandResult> findPageBySpec(PageBrandParam param){
        IPage<GunsProBrand>page=new Page<>();
        page.setCurrent(param.getPage());
        page.setSize(param.getPageSize());
        QueryWrapper<GunsProBrand> queryWrapper = new QueryWrapper<>();

        if (param.getName() != null && param.getName() != "")
            queryWrapper.like("name", param.getName().trim());

        page=baseMapper.selectPage(page,queryWrapper);

        PageResult<GunsProBrandResult>pageResult=new PageResult<>();

        pageResult.setPage(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getPages());
        pageResult.setTotalRows(page.getTotal());

        List<GunsProBrand> rows=page.getRecords();
        List<GunsProBrandResult> result = new ArrayList<>();
        rows.forEach(item -> {
            GunsProBrandResult r = new GunsProBrandResult();
            ToolUtil.copyProperties(item, r);
            result.add(r);
        });

        pageResult.setRows(result);
        return pageResult;
    }



    @Override
    public List<BrandResult> select() {

        //获取所有数据
        List<GunsProBrand>result=baseMapper.selectList(new QueryWrapper<GunsProBrand>().orderByAsc("first_char"));
        List<BrandResult> list = new ArrayList<>();

        for (GunsProBrand gunsProBrand : result) {
            BrandResult brandResult =new BrandResult();
            BeanUtil.copyProperties(gunsProBrand, brandResult, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            list.add(brandResult);
        }
        return list;
    }


    /**
     * 删除品牌信息
     * @param id 品牌id
     * @return
     */
    @Override
    public Boolean removeId(Long id) {
        //1.判断参数是否为空
        if (id==null){
            throw new ServiceException(500,"id不能为空");
        }
        //2.判断品牌下是否存在商品  如果有 无法删除品牌
        QueryWrapper<GunsProProduct>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("brand_id",id);
        List<GunsProProduct> list = productService.list(queryWrapper);
        if (list.size()>0){
            throw new ServiceException(500,"品牌下存在商品,无法删除");
        }
            //3删除
            baseMapper.deleteById(id);
        return true;
    }



    @Override
    public List<GunsProBrandResult> selectList() {

        //获取所有数据
        List<GunsProBrand>result=baseMapper.selectList(null);
        List<GunsProBrandResult> list = new ArrayList<>();

        for (GunsProBrand gunsProBrand : result) {
            GunsProBrandResult gunsProBrandResult =new GunsProBrandResult();
            BeanUtil.copyProperties(gunsProBrand, gunsProBrandResult, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            list.add(gunsProBrandResult);
        }
        return list;

    }


}
