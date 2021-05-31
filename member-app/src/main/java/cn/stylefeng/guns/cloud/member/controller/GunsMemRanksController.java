package cn.stylefeng.guns.cloud.member.controller;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemRanks;
import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.member.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.model.param.GunsMemRanksParam;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemRanksResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemRanksService;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member/ranks")
@Api(tags = "等级信息")
public class GunsMemRanksController {

    //会员等级
    @Autowired
    private GunsMemRanksService gunsMemRanksService;


    /**
     * 添加等级信息
     * @param param
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("添加等级信息")
    public ResponseData save(@Validated({AddGroup.class}) @RequestBody GunsMemRanksParam param){
        if (param.getStartScore()<0){
            return ResponseData.error("金豆不能小于0");
        }else {
            GunsMemRanks gunsMomRanks = new GunsMemRanks();
            ToolUtil.copyProperties(param, gunsMomRanks);
            gunsMomRanks.setCreateTime(new DateTime());
            Boolean result = gunsMemRanksService.save(gunsMomRanks);
            if (result){
                return ResponseData.success();
            }else{
                return ResponseData.error("保存失败");
            }
        }
    }


    /**
     * 更新等级信息
     * @param param
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("更新等级信息")
    public ResponseData update(@Validated({UpdateGroup.class}) @RequestBody GunsMemRanksParam param){
        GunsMemRanks gunsMomRanks=new GunsMemRanks();
        ToolUtil.copyProperties(param,gunsMomRanks);
        boolean result = gunsMemRanksService.updateById(gunsMomRanks);
        if (result){
            return ResponseData.success();
        }else {
            return ResponseData.error("更新失败");
        }
    }
    /**
     * 更新等级信息详情
     * @param id
     * @return
     */
    @GetMapping("/echo")
    @ApiOperation("更新等级信息详情")
    public ResponseData echo( Long id){
        GunsMemRanks gunsMomRanks= gunsMemRanksService.getById(id);
        return ResponseData.success(gunsMomRanks);
    }
    
    
    /**
     * 查询全部等级信息
     */
    @GetMapping("/selectList")
    @ApiOperation("查询等级信息")
    public ResponseData selectList(Long sort){

        List<GunsMemRanksResult>listResult= gunsMemRanksService.selectList(sort);

        return ResponseData.success(listResult);
    }



    /**
     * 模糊查询：根据名称查询等级信息
     */
    @GetMapping("/nameSelect")
    @ApiOperation("根据名称查询等级信息")
    public ResponseData nameSelect(String name){

        return ResponseData.success(gunsMemRanksService.nameSelect(name));
    }


    /**
     * 删除等级信息：判断等级下有没有会员 若存在会员信息无法删除
     */
    @PostMapping("remove")
    @ApiOperation("删除等级信息")
    public ResponseData remove(@Validated @RequestBody Map<String,Long> para){
        boolean result = gunsMemRanksService.removeId(para.get("id"));
        if (result) {
            return ResponseData.success();
        }else {
            return ResponseData.error("等级下存在会员信息，删除失败");
        }

    }

}
