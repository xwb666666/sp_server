package cn.stylefeng.guns.cloud.system.modular.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.tree.DefaultTreeBuildFactory;
import cn.stylefeng.guns.cloud.system.modular.dict.entity.SysDict;
import cn.stylefeng.guns.cloud.system.modular.dict.enums.DictExceptionEnum;
import cn.stylefeng.guns.cloud.system.modular.dict.mapper.DictMapper;
import cn.stylefeng.guns.cloud.system.modular.dict.model.DictInfo;
import cn.stylefeng.guns.cloud.system.modular.dict.model.TreeDictInfo;
import cn.stylefeng.guns.cloud.system.modular.dict.service.DictService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 基础字典 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-24
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, SysDict> implements DictService {

    @Override
    public void addDict(SysDict dict) {
        if (ObjectUtil.isEmpty(dict) || ObjectUtil.isEmpty(dict.getDictCode()) || ObjectUtil.isEmpty(dict.getDictName())) {
            throw new RequestEmptyException();
        }

        dict.setDictId(null);

        if (ObjectUtil.isEmpty(dict.getParentId())) {
            dict.setParentId(-1L); // 默认的根节点
        } else {
            // 判断字典的父id是否存在
            SysDict sysDict = this.getById(dict.getParentId());
            if (sysDict == null) {
                throw new ServiceException(DictExceptionEnum.PARENT_NOT_EXISTED);
            }
        }
        dict.setDelFlag("N");
        dict.setStatus(StatusEnum.ENABLE.getCode());
        dict.setDictId(IdWorker.getId());
        this.baseMapper.insert(dict);
    }

    @Override
    public void updateDict(SysDict dict) {
        if (ObjectUtil.isEmpty(dict)
                || ObjectUtil.isEmpty(dict.getDictId())
                || ObjectUtil.isEmpty(dict.getDictCode())
                || ObjectUtil.isEmpty(dict.getDictName())) {
            throw new RequestEmptyException();
        }

        // 不能修改类型
        dict.setDictTypeCode(null);

        SysDict oldDict = this.baseMapper.selectById(dict.getDictId());
        if (oldDict == null) {
            throw new ServiceException(DictExceptionEnum.NOT_EXISTED);
        }

        // 判断编码是否重复
        Wrapper<SysDict> wrapper = new QueryWrapper<SysDict>()
                .eq("DICT_CODE", dict.getDictCode())
                .ne("DICT_ID", oldDict.getDictId());
        int dicts = this.baseMapper.selectCount(wrapper);
        if (dicts > 0) {
            throw new ServiceException(DictExceptionEnum.REPEAT_DICT_TYPE);
        }

        BeanUtil.copyProperties(dict, oldDict, CopyOptions.create().setIgnoreNullValue(true));
        this.updateById(oldDict);
    }

    @Override
    public void deleteDict(String dictId) {
        if (ObjectUtil.isEmpty(dictId)) {
            throw new RequestEmptyException();
        }

        this.baseMapper.deleteById(dictId);
    }

    @Override
    public void updateDictStatus(String dictId, Integer status) {
        if (ObjectUtil.isEmpty(dictId) || ObjectUtil.isEmpty(status)) {
            throw new RequestEmptyException();
        }

        // 检查状态是否合法
        if (StatusEnum.toEnum(status) == null) {
            throw new ServiceException(DictExceptionEnum.WRONG_STATUS);
        }

        SysDict dict = this.baseMapper.selectById(dictId);
        dict.setStatus(status);
        this.updateById(dict);
    }

    @Override
    public List<DictInfo> getDictList(DictInfo dictInfo) {

        if (dictInfo == null) {
            dictInfo = new DictInfo();
        }

        return baseMapper.getDictList(dictInfo);
    }

    @Override
    public Page<DictInfo> getDictPage(DictInfo dictInfo) {

        Page<DictInfo> page = PageFactory.defaultPage();

        if (dictInfo == null) {
            dictInfo = new DictInfo();
        }
        List<DictInfo> dictList = baseMapper.getDictList(page, dictInfo);
        if (ObjectUtil.isEmpty(dictList)) {
            return page;
        }
        String rootId = "-1";
        for (DictInfo dict : dictList) {
            String parentId = dict.getParentId();
            if (rootId.equals(parentId)) {
                dict.setParentName("顶级");
                continue;
            }
            SysDict parentDict = this.getById(parentId);
            dict.setParentName(parentDict.getDictName());
        }
        page.setRecords(dictList);

        return page;
    }

    @Override
    public List<SysDict> getDictListByTypeCode(String dictTypeCode) {

        if (ObjectUtil.isEmpty(dictTypeCode)) {
            throw new RequestEmptyException("字典type编码为空！");
        }

        return this.baseMapper.selectList(new QueryWrapper<SysDict>().eq("DICT_TYPE_CODE", dictTypeCode));
    }

    @Override
    public List<TreeDictInfo> getTreeDictList(String dictTypeCode) {
        if (ObjectUtil.isEmpty(dictTypeCode)) {
            throw new RequestEmptyException();
        }

        // 获取对应字典类型的所有字典列表
        Wrapper<SysDict> wrapper = new QueryWrapper<SysDict>().eq("DICT_TYPE_CODE", dictTypeCode);
        List<SysDict> dicts = this.baseMapper.selectList(wrapper);
        if (dicts == null || dicts.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<TreeDictInfo> treeDictInfos = new ArrayList<>();
        for (SysDict dict : dicts) {
            TreeDictInfo treeDictInfo = new TreeDictInfo();
            treeDictInfo.setDictId(dict.getDictId());
            treeDictInfo.setDictCode(dict.getDictCode());
            treeDictInfo.setParentId(dict.getParentId());
            treeDictInfo.setDictName(dict.getDictName());
            treeDictInfos.add(treeDictInfo);
        }

        // 构建菜单树
        return new DefaultTreeBuildFactory<TreeDictInfo>().doTreeBuild(treeDictInfos);
    }

    @Override
    public List<DictInfo> getDictListByTypeCodeAndPid(String dictTypeCode, String parentCode) {
        if (ObjectUtil.isEmpty(dictTypeCode)) {
            throw new RequestEmptyException("字典type编码为空！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("dictTypeCode", dictTypeCode);
        map.put("parentCode", parentCode);
        return this.baseMapper.getDictListByTypeCodeAndPid(map);
    }

    @Override
    public boolean checkCode(String dictId, String dictCode) {
        QueryWrapper<SysDict> wrapper = new QueryWrapper<SysDict>();
        wrapper.eq("DICT_CODE", dictCode);
        if (!StrUtil.isEmpty(dictId)) {
            wrapper.ne("DICT_ID", dictId);
        }
        Integer selectCount = this.baseMapper.selectCount(wrapper);
        if (selectCount <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<DictInfo> getDictListByTypeCodeAndNotPids(String dictTypeCode, List<String> parentIds) {
        if (ObjectUtil.isEmpty(dictTypeCode)) {
            throw new RequestEmptyException("字典type编码为空！");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("dictTypeCode", dictTypeCode);
        map.put("parentIds", parentIds);
        return this.baseMapper.getDictListByTypeCodeAndNotPid(map);
    }

    @Override
    public String translateCode(String dictCode) {
        if (ObjectUtil.isEmpty(dictCode)) {
            return "";
        }
        List<String> dictNames = this.baseMapper.getDictName(dictCode);
        if (ObjectUtil.isEmpty(dictNames)) {
            return "";
        }
        return dictNames.get(0);
    }

    @Override
    public String getDictCode(String dictTypeCode, String dictName) {
        if (ObjectUtil.isEmpty(dictTypeCode) || ObjectUtil.isEmpty(dictName)) {
            return "";
        }
        List<String> dictNames = this.baseMapper.getDictCode(dictTypeCode, dictName);
        if (ObjectUtil.isEmpty(dictNames)) {
            return "";
        }
        return dictNames.get(0);
    }

    @Override
    public String idsToNames(String ids) {

        StringBuilder sb = new StringBuilder();
        if (ObjectUtil.isEmpty(ids)) {
            return sb.append("无").toString();
        }

        List<String> idList = null;
        if (ids.startsWith("[") && ids.endsWith("]")) {
            idList = JSONArray.parseArray(ids, String.class);
        } else if (!ids.startsWith("[") && !ids.endsWith("]")) {
            String[] split = ids.split(",");
            idList = Arrays.asList(split);
        }
        if (ObjectUtil.isEmpty(idList)) {
            return "无";
        }
        List<SysDict> dicts = this.baseMapper.selectBatchIds(idList);
        if (ObjectUtil.isEmpty(dicts)) {
            return sb.append("无").toString();
        }
        for (SysDict dict : dicts) {
            sb.append(",").append(dict.getDictName());
        }

        return sb.deleteCharAt(0).toString();
    }
}
