package cn.stylefeng.guns.cloud.product.service;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProGroup;

import cn.stylefeng.guns.cloud.api.product.model.result.GunsProGroupResult;
import cn.stylefeng.guns.cloud.product.model.api.result.GroupResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface GunsProGroupService extends IService<GunsProGroup> {

    List<GunsProGroupResult> getSelect();

    List<GroupResult> select();
}
