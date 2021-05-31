package cn.stylefeng.guns.cloud.product.service.Impl;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProCategory;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProCategoryResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.product.mapper.GunsProCategoryMapper;
import cn.stylefeng.guns.cloud.product.model.api.result.ApiGunsProCategoryResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CategoryResult;
import cn.stylefeng.guns.cloud.product.service.GunsProCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GunsProCategoryServiceImpl extends ServiceImpl<GunsProCategoryMapper, GunsProCategory> implements GunsProCategoryService {

    /**
     * 获取所有分类
     *
     * @return
     */
    @Override
    public List<GunsProCategoryResult> getCateWithTree() {
        //1、获取所有分类
        List<GunsProCategory> categories = baseMapper.selectList(null);
        List<GunsProCategoryResult> root = new ArrayList<>();
        categories.forEach(item -> {
            GunsProCategoryResult result=new GunsProCategoryResult();
            ToolUtil.copyProperties(item,result);
            root.add(result);
        });
        //2 获取所有一级分类
        List<GunsProCategoryResult> one = root.stream().filter(entity ->(entity.getParentId() == null || entity.getParentId() == 0))
                .map((menu -> {
                    menu.setChildren(this.getChildrens(menu, root));
                    return menu;
                })).collect(Collectors.toList());
        return one;
    }


    private List<GunsProCategoryResult> getChildrens(GunsProCategoryResult root, List<GunsProCategoryResult> all) {
        List<GunsProCategoryResult> children = all.stream().filter(entity -> {
            return entity.getParentId().equals(root.getId());
        }).map(item -> {
            //1、找到子菜单
            item.setChildren(getChildrens(item,all));
            return item;
        }).sorted((menu1, menu2) -> {
            //2、菜单的排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }

    @Override
    public List<GunsProCategoryResult> getAllCate(Long id) {
        //1、获取所有分类
        List<GunsProCategory> categories = baseMapper.selectList(null);
        List<GunsProCategoryResult> root = new ArrayList<>();
        categories.forEach(item -> {
            GunsProCategoryResult result=new GunsProCategoryResult();
            ToolUtil.copyProperties(item,result);
            root.add(result);
        });
        //2 获取所有一级分类
        List<GunsProCategoryResult> one = root.stream().filter(entity ->(entity.getParentId().equals(id)))
                .map((menu -> {
                    menu.setChildren(this.getChildrens(menu, root));
                    return menu;
                })).collect(Collectors.toList());
        return one;
    }

    @Override
    public List<GunsProCategoryResult> CategoryList() {
        //1、获取所有分类
        List<GunsProCategory> categories = baseMapper.selectList(null);
        List<GunsProCategoryResult> root = new ArrayList<>();
        categories.forEach(item -> {
            GunsProCategoryResult result=new GunsProCategoryResult();
            ToolUtil.copyProperties(item,result);
            root.add(result);
        });

        //2 获取所有一级分类
        List<GunsProCategoryResult> one = root.stream().filter(entity ->(entity.getParentId() == null || entity.getParentId() == 0))
                .map((menu -> {
                    menu.setChildren(this.getCategory(menu, root));
                    return menu;
                })).collect(Collectors.toList());
            return one;
    }

    @Override
    public List<ApiGunsProCategoryResult> OneCategoryList() {

        QueryWrapper<GunsProCategory>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        List<GunsProCategory> gunsProCategories = baseMapper.selectList(queryWrapper);

        List<ApiGunsProCategoryResult> root = new ArrayList<>();
        gunsProCategories.forEach(item -> {
            ApiGunsProCategoryResult result = new ApiGunsProCategoryResult();
            ToolUtil.copyProperties(item, result);
            root.add(result);

            });
         return root;

    }



    @Override
    @Transactional
    public Boolean removeId(Long id) {
        List<GunsProCategoryResult> childrens = this.getAllCate(id);
        for(GunsProCategoryResult gpcr :childrens){
            Long cId = gpcr.getId();
            this.removeById(cId);
            if(gpcr.getChildren()!=null){
                for(GunsProCategoryResult children :gpcr.getChildren()){
                    this.removeById(children.getId());
                }
            }
        }
        return true;
    }



    /**
     * 递归删除
     * @param all
     * @return
     */
    private Boolean deleteChildrens(List<GunsProCategoryResult> all) {
        for(GunsProCategoryResult gpcrChild :all){
            //1. 判断。。。。。
            if(gpcrChild.getParentId()!=null){
                deleteChildrens(gpcrChild.getChildren());
            }
            this.removeById(gpcrChild.getId());
        }
        return true;
    }

    private List<GunsProCategoryResult> getCategory(GunsProCategoryResult root, List<GunsProCategoryResult> all) {
        List<GunsProCategoryResult> children = all.stream().filter(entity -> {
            return entity.getParentId().equals(root.getId());
        }).map(item -> {
            //1、找到子菜单
            item.setChildren(getCategory(item,all));
            return item;
        }).collect(Collectors.toList());

        return children;
    }


}
