package cn.stylefeng.guns.cloud.product.service.Impl;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProBrowse;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.product.mapper.BrowseMapper;
import cn.stylefeng.guns.cloud.product.model.api.param.BrowseParam;
import cn.stylefeng.guns.cloud.product.model.api.result.BrowseResult;
import cn.stylefeng.guns.cloud.product.service.BrowseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrowseServiceImpl extends ServiceImpl<BrowseMapper, GunsProBrowse> implements BrowseService {


    @Override
    public Page<Map<String,Object>> selectList(BrowseParam param) {

        Page<BrowseResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        page.setRecords(baseMapper.selectList(page, param));

        Map<Date,List<BrowseResult>> list = page.getRecords().stream().collect(Collectors.groupingBy(BrowseResult::getCreateTime));

        List<Map<String,Object>> dataList = new ArrayList();

        for (Map.Entry<Date, List<BrowseResult>> entry : list.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            Map<String,Object> resultItem = new HashMap<>();
            resultItem.put("date",new SimpleDateFormat("yyyy-MM-dd").format(entry.getKey()));
            resultItem.put("list",entry.getValue());
            dataList.add(resultItem);
        }

        List<Map<String,Object>> resultList =  dataList.stream().sorted(new Comparator<Map<String,Object>>() {
            @Override
            public int compare(Map<String,Object> object1, Map<String,Object> object2) {

                String objectStr1 = (String) object1.get("date");
                String objectStr2 = (String) object2.get("date");

                return objectStr2.compareTo(objectStr1);
            }
        }).collect(Collectors.toList());

        Page<Map<String,Object>> pageResult = new Page<>();
        pageResult.setSize(page.getSize());
        pageResult.setCurrent(page.getCurrent());
        pageResult.setTotal(page.getTotal());
        pageResult.setPages(page.getPages());
        pageResult.setRecords(resultList);

        return pageResult;
    }


    @Override
    public Boolean addSave(GunsProBrowse gunsProBrowse) {
        QueryWrapper<GunsProBrowse>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",gunsProBrowse.getProductId()).eq("member_id",gunsProBrowse.getMemberId());
        GunsProBrowse browse = baseMapper.selectOne(queryWrapper);
        if (browse!=null){
            throw new ServiceException(500,"浏览记录已存在，不能重复添加");
        }
        gunsProBrowse.setCreateTime(new DateTime());
        baseMapper.insert(gunsProBrowse);
        return true;
    }


    @Override
    public Boolean removeIds(Long[] ids) {
        List<Long> idList= Arrays.asList(ids);
        baseMapper.deleteBatchIds(idList);
        return true;
    }

}
