package cn.stylefeng.guns.cloud.member.controller;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemIntegralDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemParam;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemberQueryParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemIntegralDetailResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.model.param.GunsMemberParam;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemSelectResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemIntegralDetailService;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/member")
@Api(tags = "会员信息")
public class GunsMemController {

    @Autowired
    private GunsMemService service;

    @Autowired
    private GunsMemIntegralDetailService detailService;

    /**
     * 添加会员
     * @param param
     * @return
     */
    @PostMapping("save")
    @ApiOperation("添加会员信息")
    public ResponseData save(@Validated({AddGroup.class}) @RequestBody GunsMemberParam param) {

        service.save(param);

        return ResponseData.success();

    }

    /**
     * 删除会员信息
     * @param param
     * @return
     */
    @PostMapping("delete")
    @ApiOperation("删除会员信息")
    public ResponseData delete(@RequestBody Map<String,Long> param) {

        boolean remove = service.remove(param.get("id"));
        if (remove) {
            return ResponseData.success();
        } else {
            return ResponseData.error("删除失败");
        }
    }

    /**
     * 修改会员信息
     * @param param
     * @return
     */
    @PostMapping("update")
    @ApiOperation("修改会员信息")
    public ResponseData update( @RequestBody @Validated GunsMemParam param) {
        Boolean updateParam = service.update(param);
        if (updateParam) {
            return ResponseData.success();
        }else {
            return ResponseData.error("修改失败");
        }

    }


    /**
     * 分页查询
     * @param param
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("分页查询所有会员信息")
    public ResponseData getPageList(GunsMemberQueryParam param) {
        Page<GunsMemSelectResult> pageList = service.getPageList(param);
        return ResponseData.success(pageList);
    }


    /**
     *查看数据修改详情
     * @param
     * @return
     */
    @GetMapping("/echo")
    @ApiOperation("数据详情")
    public ResponseData echo(Long id){
        if(id==null){
            throw new ServiceException(500,"id不能为空");
        }

        GunsMemParamResult gunsMemParam=service.getById(id);
        return ResponseData.success(gunsMemParam);
    }


    /**
     * 查询金豆明细
     * @param param
     * @return
     */
    @GetMapping("/Integral/selectList")
    @ApiOperation("查询金豆明细")
    public ResponseData selectList(@Validated GunsMemIntegralDetailParam param){


        Page<GunsMemIntegralDetailResult>getSelectList=detailService.getSelectList(param);

        return ResponseData.success(getSelectList);
    }

}
