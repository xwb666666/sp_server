package cn.stylefeng.guns.cloud.product.model.api.result;

import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementResult;
import cn.stylefeng.guns.cloud.api.member.model.result.ApiAnnouncementResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.api.product.model.result.ApiAdvertisingResult;
import cn.stylefeng.guns.cloud.api.product.model.result.ApiShowcaseResult;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import lombok.Data;

import java.util.List;

@Data
public class HomeResult {

  private  MemberInfoResult memberInfoResult;                               //用户信息

  private List<GroupResult> groupResult;                                    //分组

  private List<ApiAdvertisingResult> apiAdvertisingResultList;              //广告图片

  private List<ApiShowcaseResult> apiShowcaseResultList;                    //橱窗图片

  private PageResult<ProductResult>seckillTodayResult;                      //今日秒杀

  private PageResult<ProductResult>brandActivityResult;                     //品牌活动

  private PageResult<ProductResult>hotListResult;                           //热销榜

  private PageResult<ProductResult>categoryProductListResult;               //分类商品信息

  private List<ApiGunsProCategoryResult>apiGunsProCategoryResults;          //分类信息

  private PageResult<ApiAnnouncementResult> apiAnnouncementResultPageResult;  //查询公告标题列表

  private ApiAnnouncementResult apiAnnouncementResult;                      //首页公告


}
