package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysArea;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysAreaMapper;

import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysAreaResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAreaService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 基础字典 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements SysAreaService {
    /*@Override
    public List<SysAreaResult> getCateWithTree() {
        //1.获取所有区域
        List<SysArea> sysAreas=baseMapper.selectList(null);
        List<SysAreaResult> list=new ArrayList<>();
        sysAreas.forEach(item ->{
            SysAreaResult result=new SysAreaResult();
            ToolUtil.copyProperties(item,result);
            list.add(result);
        });
        return list;
    }*/


    @Override
    public List<SysAreaResult> getCateWithTree() {
        //1.获取所有区域
        List<SysArea> sysAreas=baseMapper.selectList(null);
        List<SysAreaResult> list=new ArrayList<>();
        sysAreas.forEach(item ->{
            SysAreaResult result=new SysAreaResult();
            ToolUtil.copyProperties(item,result);
            list.add(result);
        });

        //2 获取所有一级分类
        List<SysAreaResult> one = list.stream().filter(entity ->(entity.getParentId() == null || entity.getParentId() == 0))
                .map((oneList -> {
                    oneList.setChildren(this.getChildrens(oneList, list));
                    return oneList;
                })).collect(Collectors.toList());
        return one;
    }

    private List<SysAreaResult> getChildrens(SysAreaResult result, List<SysAreaResult> all) {
        List<SysAreaResult> children = all.stream().filter(entity -> result.getId().equals(entity.getParentId())).map(item -> {
            //1、找到子菜单
            item.setChildren(getChildrens(item,all));
            return item;
        }).collect(Collectors.toList());

        return children;
    }

}
