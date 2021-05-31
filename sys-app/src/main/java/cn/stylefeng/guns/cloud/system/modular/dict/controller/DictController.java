package cn.stylefeng.guns.cloud.system.modular.dict.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.model.web.response.SuccessResponseData;
import cn.stylefeng.guns.cloud.system.modular.dict.entity.SysDict;
import cn.stylefeng.guns.cloud.system.modular.dict.model.DictInfo;
import cn.stylefeng.guns.cloud.system.modular.dict.model.TreeDictInfo;
import cn.stylefeng.guns.cloud.system.modular.dict.service.DictService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * 字典管理
 *
 * @author fengshuonan
 * @Date 2018/7/25 下午12:47
 */
@RestController
@ApiResource(name = "字典管理", path = "/dict")
@Api(tags = "字典信息接口")
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 添加字典
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("添加字典")
    @PostResource(name = "添加字典", path = "/addDict")
    public ResponseData addDictType(@RequestBody SysDict dict) {
        this.dictService.addDict(dict);
        return new SuccessResponseData();
    }

    /**
     * 修改字典
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("修改字典")
    @PostResource(name = "修改字典", path = "/updateDict")
    public ResponseData updateDict(@RequestBody SysDict dict) {
        this.dictService.updateDict(dict);
        return new SuccessResponseData();
    }

    /**
     * 删除字典
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("删除字典")
    @PostResource(name = "删除字典", path = "/deleteDict")
    public ResponseData deleteDict(@RequestParam("dictId") String dictId) {
        this.dictService.deleteDict(dictId);
        return new SuccessResponseData();
    }

    /**
     * 更新字典状态
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("更新字典状态")
    @PostResource(name = "更新字典状态", path = "/updateDictStatus")
    public ResponseData updateDictStatus(@RequestParam("dictId") String dictId,
                                                        @RequestParam("status") Integer status) {
        this.dictService.updateDictStatus(dictId, status);
        return new SuccessResponseData();
    }

    /**
     * 获取字典列表(分页)
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("获取字典列表(分页)")
    @PostResource(name = "获取字典列表", path = "/getDictPage")
    public ResponseData getDictPage(@RequestBody DictInfo dictInfo) {
        Page<DictInfo> page = this.dictService.getDictPage(dictInfo);
        return new SuccessResponseData(page);
    }

    /**
     * 获取字典列表
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("获取字典列表(不分页)")
    @PostResource(name = "获取字典列表", path = "/getDictList")
    public ResponseData getDictList(@RequestBody DictInfo dictInfo) {
        List<DictInfo> dictList = this.dictService.getDictList(dictInfo);
        return new SuccessResponseData(dictList);
    }

    /**
     * 根据字典类型code获取所有字典
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("根据字典类型code获取所有字典")
    @PostResource(name = "根据字典类型code获取所有字典", path = "/getDictListByTypeCode")
    public ResponseData getDictListByTypeCode(@RequestParam("dictTypeCode") String dictTypeCode) {
        List<SysDict> dictList = this.dictService.getDictListByTypeCode(dictTypeCode);
        return new SuccessResponseData(dictList);
    }

    /**
     * 获取树形字典列表
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("根据字典类型code获取树形字典列表")
    @PostResource(name = "获取树形字典列表", path = "/getDictTreeList")
    public ResponseData getDictTreeList(@RequestParam("dictTypeCode") String dictTypeCode) {
        List<TreeDictInfo> treeDictList = this.dictService.getTreeDictList(dictTypeCode);
        return new SuccessResponseData(treeDictList);
    }

    /**
     * 根据字典类型code和父编码获取下级字典
     *
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("根据字典类型code和父编码获取下级字典")
    @PostResource(name = "根据字典类型code和父编码获取下级字典", path = "/getListByTypeCodeAndPCode")
    public ResponseData getListByTypeCodeAndPCode(@RequestParam("dictTypeCode") String dictTypeCode,
                                                                 @RequestParam("parentCode") String parentCode) {
        List<DictInfo> dicts = dictService.getDictListByTypeCodeAndPid(dictTypeCode, parentCode);
        return new SuccessResponseData(dicts);
    }

    /**
     * code校验
     *
     * @return true:可以使用；false：不能使用
     * @author fengshuonan
     * @Date 2018/7/25 下午12:36
     */
    @ApiOperation("code校验")
    @PostResource(name = "code校验", path = "/checkCode")
    public ResponseData checkCode(@RequestParam("dictId") String dictId, @RequestParam("dictCode") String dictCode) {
        boolean flag = this.dictService.checkCode(dictId, dictCode);

        return new SuccessResponseData(flag);
    }
}
