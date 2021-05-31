package cn.stylefeng.guns.cloud.product.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.api.member.model.result.ApiAnnouncementResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProProduct;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProSku;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProSkuName;
import cn.stylefeng.guns.cloud.api.product.model.params.*;
import cn.stylefeng.guns.cloud.api.product.model.result.*;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.product.config.AppConfig;
import cn.stylefeng.guns.cloud.product.consumer.RemoteMemberService;
import cn.stylefeng.guns.cloud.product.consumer.RemoteSystemService;
import cn.stylefeng.guns.cloud.product.mapper.GunsProProductMapper;
import cn.stylefeng.guns.cloud.product.model.api.ProductEsModel;
import cn.stylefeng.guns.cloud.product.model.api.param.ApiProductDetailParam;
import cn.stylefeng.guns.cloud.product.model.api.param.SearchParam;
import cn.stylefeng.guns.cloud.product.model.api.result.*;
import cn.stylefeng.guns.cloud.product.service.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class GunsProductServiceImpl extends ServiceImpl<GunsProProductMapper, GunsProProduct> implements GunsProductService {

    @Resource
    GunsProSkuNameService gunsProSkuNameService;

    @Resource
    GunsProSkuService skuService;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private RemoteMemberService remoteMemberService;

    @Autowired
    private RemoteSystemService remoteSystemService;

    @Resource
    private GunsProGroupService gunsProGroupService;

    @Resource
    private GunsProCategoryService gunsProCategoryService;

    @Resource
    private GunsProductService  productService;

    @Resource
    private CollectService collectService;



    private final String mall_index = "hc_mall_product";

    /**
     * 私有方法，保存和修改通用
     *
     * @param skuNameItems
     * @return
     */
    private List<GunsProSkuName> getSkuNameInfos(List<GunsProSkuNameItem> skuNameItems) {
        List<GunsProSkuName> result = new ArrayList<>();
        if (skuNameItems != null && skuNameItems.size() > 0) {
            skuNameItems.forEach(item -> {
                //父级
                GunsProSkuName skuNameEntity = gunsProSkuNameService.saveOrGetSkuName(item.getName(), 0l);
                result.add(skuNameEntity);
                //子级
                List<GunsProSkuNameItem> children = item.getChildren();
                if (children != null && children.size() > 0) {
                    children.forEach(child -> {
                        GunsProSkuName childSkuNameEntity = gunsProSkuNameService.saveOrGetSkuName(child.getName(), skuNameEntity.getId());
                        result.add(childSkuNameEntity);
                    });
                }
            });
        }
        return result;
    }

    @Override
    @Transactional
    public boolean addProduct(GunsProProductParam param) {

        Date createTime = DateTime.now();
        GunsProProduct productBase = new GunsProProduct();
        ToolUtil.copyProperties(param, productBase); //给实体赋值
        productBase.setCreateDate(createTime);
        baseMapper.insert(productBase); //插入商品
        if (param.getIsSku() == 1) //启用多规格
        {
            //保存sku 信息
            List<GunsProSkuParam> gunsProSkuParams = param.getSkuItems();
            //插入并返回所有商品规格名称信息
            List<GunsProSkuName> skuNamesEntitys = getSkuNameInfos(param.getSkuNameItems());

            if (gunsProSkuParams != null && gunsProSkuParams.size() > 0) {
                List<GunsProSku> skuEntities = gunsProSkuParams.stream().map(item -> {
                    GunsProSku skuEntity = new GunsProSku();
                    ToolUtil.copyProperties(item, skuEntity);
                    List<SkuAndSkuNameItem> skuNameItems = item.getSkuNames();

                    if (skuNameItems == null)
                        throw new RequestEmptyException("sku信息里面skuname参数为空");

                    List<String> skuNameArray = new ArrayList<>();
                    List<Long> skuNameIdArray = new ArrayList<>();
                    skuNameItems.forEach(s -> {
                        GunsProSkuName parent = skuNamesEntitys.stream().filter(
                                n -> n.getName().equals(s.getName()) && n.getParentId() == 0)
                                .findFirst().get();

                        GunsProSkuName child = skuNamesEntitys.stream().filter(n -> n.getParentId() == parent.getId() && n.getName().equals(s.getItem().getName()))
                                .findFirst().get();
                        skuNameArray.add(child.getName());
                        skuNameIdArray.add(child.getId());
                    });

                    skuEntity.setSkuNames(JSONUtil.toJsonStr(skuNameArray));
                    skuEntity.setSkuNameIds(JSONUtil.toJsonStr(skuNameIdArray));
                    skuEntity.setProductId(productBase.getId());
                    skuEntity.setCreateDate(createTime);
                    return skuEntity;
                }).collect(Collectors.toList());
                //构造需要的json数据，保存到产品表里面
                List<ProductSkuNameItem> skuInfo_array = new ArrayList<>();
                skuNamesEntitys.forEach(n -> {
                    if (n.getParentId() == 0) {
                        ProductSkuNameItem item = new ProductSkuNameItem();
                        item.setId(n.getId());
                        item.setName(n.getName());


                        List<GunsProSkuName> child = skuNamesEntitys.stream().filter(f ->
                                f.getParentId().equals(n.getId())
                        ).collect(Collectors.toList());

                        List<ProductSkuNameItem> child_array = new ArrayList<>();
                        child.forEach(c -> {
                            ProductSkuNameItem child_item = new ProductSkuNameItem();
                            child_item.setId(c.getId());
                            child_item.setName(c.getName());
                            child_array.add(child_item);
                        });
                        item.setItems(child_array);
                        skuInfo_array.add(item);
                    }
                });
                productBase.setSkuInfo(JSONUtil.toJsonStr(skuInfo_array));
                baseMapper.updateById(productBase); //更新
                //批量保存sku信息
                skuService.saveBatch(skuEntities);
            }
        }
        return true;
    }


    @Override
    public boolean deleteById(Long id) {

        GunsProProduct entity = baseMapper.selectById(id);
        if (entity == null)
            throw new ServiceException(500, "商品不存在");
        if (entity.getStatus() == 2)
            throw new ServiceException(500, "已上架的商品不能删除");
        entity.setStatus(0);
        entity.setUpdateDate(DateTime.now());
        this.updateById(entity);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long productId) {

        GunsProProduct entity = baseMapper.selectById(productId);
        if (entity == null)
            throw new ServiceException(500, "商品不存在");
        if (entity.getStatus() != 0)
            throw new ServiceException(500, "只有在回收站内的商品才能删除");

        QueryWrapper<GunsProSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        List<Long> skuList = skuService.list(queryWrapper).stream().map(item -> item.getId()).collect(Collectors.toList());
        skuService.removeByIds(skuList);
        this.removeById(productId);
        return true;
    }

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @Override
    public GunsProProductResult detail(Long id) {
        GunsProProductResult result = new GunsProProductResult();
        //1、查询商品信息
        GunsProProduct entity = baseMapper.selectById(id);

        if (entity == null)
            throw new ServiceException(500, "不存在的商品id");

        ToolUtil.copyProperties(entity, result);

        result.setSkuInfo(JSONObject.parseArray(entity.getSkuInfo(), ProductSkuNameItem.class));
        //通过id查询所有sku 信息
        List<GunsProSku> skuList = skuService.list(new QueryWrapper<GunsProSku>().eq("product_id", id));

        List<GunsProSkuResult> skuResultList = new ArrayList<>();

        if (skuList != null && skuList.size() > 0) {
            skuList.forEach(item -> {
                GunsProSkuResult r = new GunsProSkuResult();
                ToolUtil.copyProperties(item, r);
                r.setSkuNames(JSONObject.parseArray(item.getSkuNames(), String.class));
                skuResultList.add(r);

            });
        }
        if (skuResultList.size() > 0)
            result.setSkuList(skuResultList);

        return result;
    }

    @Override
    @Transactional
    public boolean update(GunsProProductParam param) {
        Date now = DateTime.now();
        if (param.getId() == null || param.getId() == 0)
            throw new RequestEmptyException("商品ID不能为空");
        //1、查询商品信息
        GunsProProduct entity = baseMapper.selectById(param.getId());
        if (entity == null)
            throw new ServiceException(500, "要修改的商品不存在");

        ToolUtil.copyProperties(param, entity); //给实体赋值
        entity.setUpdateDate(now); //设置修改时间
        //baseMapper.updateById(entity); //插入商品

        if (param.getIsSku() == 1d) //启用多规格
        {
            //保存sku 信息
            List<GunsProSkuParam> gunsProSkuParams = param.getSkuItems();
            //插入并返回所有商品规格名称信息
            List<GunsProSkuName> skuNamesEntitys = getSkuNameInfos(param.getSkuNameItems());

            if (gunsProSkuParams != null && gunsProSkuParams.size() > 0) {
                List<GunsProSku> skuEntities = gunsProSkuParams.stream().map(item -> {
                    GunsProSku skuEntity = new GunsProSku();
                    ToolUtil.copyProperties(item, skuEntity);
                    List<SkuAndSkuNameItem> skuNameItems = item.getSkuNames();

                    if (skuNameItems == null)
                        throw new RequestEmptyException("sku信息里面skuname参数为空");

                    List<String> skuNameArray = new ArrayList<>();
                    List<Long> skuNameIdArray = new ArrayList<>();
                    skuNameItems.forEach(s -> {
                        GunsProSkuName parent = skuNamesEntitys.stream().filter(
                                n -> n.getName().equals(s.getName()) && n.getParentId() == 0)
                                .findFirst().get();

                        GunsProSkuName child = skuNamesEntitys.stream().filter(n -> n.getParentId() == parent.getId() && n.getName().equals(s.getItem().getName()))
                                .findFirst().get();
                        skuNameArray.add(child.getName());
                        skuNameIdArray.add(child.getId());
                    });

                    skuEntity.setSkuNames(JSONUtil.toJsonStr(skuNameArray));
                    skuEntity.setSkuNameIds(JSONUtil.toJsonStr(skuNameIdArray));
                    skuEntity.setProductId(entity.getId());

                    if (skuEntity.getId() != null || skuEntity.getId() != 0d) {
                        skuEntity.setUpdateDate(now);
                    } else {
                        skuEntity.setCreateDate(now);
                    }
                    return skuEntity;
                }).collect(Collectors.toList());
                //构造需要的json数据，保存到产品表里面
                List<ProductSkuNameItem> skuInfo_array = new ArrayList<>();
                skuNamesEntitys.forEach(n -> {
                    if (n.getParentId() == 0) {
                        ProductSkuNameItem item = new ProductSkuNameItem();
                        item.setId(n.getId());
                        item.setName(n.getName());


                        List<GunsProSkuName> child = skuNamesEntitys.stream().filter(f ->
                                f.getParentId().equals(n.getId())
                        ).collect(Collectors.toList());

                        List<ProductSkuNameItem> child_array = new ArrayList<>();
                        child.forEach(c -> {
                            ProductSkuNameItem child_item = new ProductSkuNameItem();
                            child_item.setId(c.getId());
                            child_item.setName(c.getName());
                            child_array.add(child_item);
                        });
                        item.setItems(child_array);
                        skuInfo_array.add(item);
                    }
                });
                entity.setSkuInfo(JSONUtil.toJsonStr(skuInfo_array));
                baseMapper.updateById(entity); //更新
                //批量保存sku信息
                List<GunsProSku> addEntities = skuEntities.stream().filter(n -> n.getId() == 0 || n.getId() == null).collect(Collectors.toList());
                skuService.saveBatch(addEntities);
                List<GunsProSku> updateEntities = skuEntities.stream().filter(n -> n.getId() > 0).collect(Collectors.toList());
                skuService.updateBatchById(updateEntities); //更新
            }
        }
        return true;
    }

    @Override
    public PageResult<GunsProListResult> getProductList(GunsProQueryParam param) {
        if (param.getPage() == null || param.getPage() == 0)
            param.setPage(1l);
        if (param.getPageSize() == null || param.getPageSize() == 0)
            param.setPageSize(20l);

        IPage<GunsProProduct> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        QueryWrapper<GunsProProduct> queryWrapper = new QueryWrapper<>();

        if (param.getName() != null && param.getName() != "")
            queryWrapper.like("name", param.getName().trim());
        if (param.getCode() != null && param.getCode() != "")
            queryWrapper.eq("code", param.getCode());
        if (param.getStatus() != null)
            queryWrapper.eq("status", param.getStatus());
        if (param.getCateId() != null && param.getCateId() > 0)
            queryWrapper.eq("category_id", param.getCateId());

        page = baseMapper.selectPage(page, queryWrapper);
        PageResult<GunsProListResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        List<GunsProProduct> rows = page.getRecords();
        List<GunsProListResult> rows_result = new ArrayList<>();
        rows.forEach(item -> {
            GunsProListResult r = new GunsProListResult();
            ToolUtil.copyProperties(item, r);
            rows_result.add(r);
        });
        result.setRows(rows_result);
        return result;
    }


    @Override
    public PageResult<SearchResult> searchFromEs(SearchParam param) throws IOException {
        PageResult<SearchResult> result = new PageResult<>();
        SearchRequest searchRequest=builderSearchRequest(param);
        SearchResponse response = restHighLevelClient.search(searchRequest, AppConfig.COMMON_OPTIONS);

        System.out.println(response);

        SearchHits hits = response.getHits();
        long totalCount = hits.getTotalHits().value;
        Long totalPage=totalCount%param.getPageSize()==0?(totalCount/param.getPageSize()):(totalCount/param.getPageSize())+1;
        result.setTotalPage(totalPage);
        result.setTotalRows(totalCount);
        List<SearchResult> list=new ArrayList<>();
        for(SearchHit hit:hits)
        {
            String sourceAsString = hit.getSourceAsString();
            SearchResult model = JSON.parseObject(sourceAsString, SearchResult.class);
            list.add(model);
        }
        result.setRows(list);
        return result;
    }

    private SearchRequest builderSearchRequest(SearchParam param)
    {
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder(); //构建dsl语句
        BoolQueryBuilder boolQuery= QueryBuilders.boolQuery();
        //关键字匹配
        if(!StringUtils.isEmpty(param.getKeywords()))
        {
            boolQuery.must(QueryBuilders.matchQuery("name",param.getKeywords()));
        }
        //品牌过滤
        if(param.getBrandId()!=null)
        {
            boolQuery.filter(QueryBuilders.termQuery("brand_id",param.getBrandId()));
        }
        //分类过滤
        if(param.getCateId()!=null)
        {
            boolQuery.filter(QueryBuilders.termQuery("category_id",param.getCateId()));
        }

        if(param.getSort()==1) //根据商品发布时间排序
        {
            sourceBuilder.sort("create_time",param.getSortType()==1? SortOrder.DESC:SortOrder.ASC);
        }

        //价格排序
        sourceBuilder.sort("present_price",param.getSaleSortType()==0?SortOrder.ASC:SortOrder.DESC);

        //分页
        sourceBuilder.from((param.getPage()-1)*param.getPageSize());
        sourceBuilder.size(param.getPageSize());

        sourceBuilder.query(boolQuery);
        SearchRequest searchRequest=new SearchRequest(new String[]{mall_index},sourceBuilder);
        return searchRequest;
    }

    /**
     * 商品上架
     *
     * @param id 传入的商品id
     * @return
     */
    @Override
    public boolean upToSale(Long id) throws IOException {
        GunsProProduct product = baseMapper.selectById(id);
        if (product == null)
            throw new ServiceException(500, "不存在该商品");
        if (product.getStatus() == 0)
            throw new ServiceException(500, "商品已经删除");
        if (product.getStatus() == 2)
            throw new ServiceException(500, "商品已经上架,无需再次操作");

        /**
         * 1、商品发送到es中，以备搜索(待做)
         * 2、更新商品信息为已经上架
         */

        IndexRequest request = new IndexRequest(mall_index);

        request.id(product.getId().toString());
        ProductEsModel model = new ProductEsModel();
        ToolUtil.copyProperties(product, model);
        model.setId(product.getId());
        model.setCategory_id(product.getCategoryId());
        model.setCreate_time(DateTime.of(product.getCreateDate()).toString());
        model.setSales_volume(product.getSalesVolume());
        model.setBrand_id(product.getBrandId());
        model.setOriginal_price(product.getOriginalPrice());
        model.setPresent_price(product.getPresentPrice());
        model.setDesc(product.getDes());
        model.setTotal_stock(product.getTotalStock());
        //查找sku信息

        List<GunsProSku> skus= skuService.list(new QueryWrapper<GunsProSku>().eq("product_id",product.getId()));
        List<ProductEsModel.EsSkuModel> esSkuList=new ArrayList<>();
        skus.forEach(item->{
            ProductEsModel.EsSkuModel sku=new ProductEsModel.EsSkuModel();
            sku.setName(item.getName());
            sku.setSkuId(item.getId());
            sku.setCode(item.getCode());
            sku.setOriginal_price(item.getOriginalPrice());
            sku.setPresent_price(item.getPresentPrice());
            sku.setPicture(item.getPicture());
            sku.setCost(item.getCost());
            sku.setStock(item.getStock());
            esSkuList.add(sku);
        });
        model.setSkus(esSkuList);
        String json = JSON.toJSONString(model);
        System.out.println(json);
        request.source(json, XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, AppConfig.COMMON_OPTIONS);
        Date upTime = DateTime.now();
        product.setStatus(2);
        product.setPutOnDate(upTime);
        product.setUpdateDate(upTime);
        baseMapper.updateById(product); //更新
        return true;
    }

    /**
     * 商品下架功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean downFromSale(Long id) throws IOException {
        GunsProProduct product = baseMapper.selectById(id);
        if (product == null)
            throw new ServiceException(500, "不存在该商品");
        if (product.getStatus() == 0)
            throw new ServiceException(500, "商品已经删除");
        if (product.getStatus() == 1)
            throw new ServiceException(500, "商品已经下架,无需再次操作");
        /**
         * 1、删除es中商品信息(待做)
         * 2、更新商品信息为已经下架
         */
        DeleteRequest request = new DeleteRequest(mall_index);
        request.id(id.toString());
        DeleteResponse delete = restHighLevelClient.delete(request, AppConfig.COMMON_OPTIONS);
        if (delete.getResult().equals(DocWriteResponse.Result.DELETED)) {
            Date downTime = DateTime.now();
            product.setStatus(3);
            product.setLowOffDate(downTime);
            product.setUpdateDate(downTime);
            baseMapper.updateById(product); //更新
            return true;
        } else
            return false;

    }

    @Override
    public ApiProductDetailResult apiProductDetail(ApiProductDetailParam param) {

        ApiProductDetailResult result = new ApiProductDetailResult();
        //1、查询商品信息
        GunsProProduct entity = baseMapper.selectById(param.getId());

        if (entity == null)
            throw new ServiceException(500, "不存在的商品id");

        ToolUtil.copyProperties(entity, result);

        System.out.println("========================== skuinfo ===================");
        System.out.println(entity.getSkuInfo());
        result.setSkuNames(JSONObject.parseArray(entity.getSkuInfo(), ApiProductDetailResult.SkuNameItem.class));
        //通过id查询所有sku 信息
        List<GunsProSku> skuList = skuService.list(new QueryWrapper<GunsProSku>().eq("product_id", param.getId()));

        List<ApiProductDetailResult.SkuItem> skuResultList = new ArrayList<>();

        if (skuList != null && skuList.size() > 0) {
            skuList.forEach(item -> {
                ApiProductDetailResult.SkuItem r = new ApiProductDetailResult.SkuItem();
                ToolUtil.copyProperties(item, r);
                r.setSkuNames(JSONObject.parseArray(item.getSkuNames(), String.class));
                skuResultList.add(r);

            });
        }
        if (skuResultList.size() > 0)
            result.setSkuList(skuResultList);

        QueryWrapper<GunsProCollect>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id",param.getMemberId()).eq("product_id",param.getId());
        GunsProCollect one = collectService.getOne(queryWrapper);
        if (one!=null){
            result.setIsCollected(1);
            result.setCollectId(one.getId());
        }
        return result;

    }



    /**
     * 应用服务：获取实时商品信息
     * @param list
     * @return
     */
    @Override
    public List<RemoteProductResult> getRealProducts(List<RomteProductSkuParam> list) {
        List<RemoteProductResult> result = new ArrayList<RemoteProductResult>();
        list.forEach(item -> {
            GunsProProduct entity = baseMapper.selectById(item.getProductId());
            if(entity.getIsSku()==1){
                GunsProSku sku = skuService.getById(item.getSkuId());
                entity.setPresentPrice(sku.getPresentPrice());
                entity.setOriginalPrice(sku.getOriginalPrice());
                entity.setCost(BigDecimal.valueOf(sku.getCost()));
            }
            RemoteProductResult remoteProductResult = new RemoteProductResult();
            ToolUtil.copyProperties(entity, remoteProductResult);
            result.add(remoteProductResult);
        });
        return result;
    }

    /**
     * 应用服务：修改商品库存
     * @param
     * @return
     */
    @Override
    @Transactional
    public synchronized boolean editProductStock(Map<String,Double> map) {
        Double productId = Double.valueOf(String.valueOf(map.get("productId")));
        Double quantity = Double.valueOf(String.valueOf(map.get("quantity")));
        Double skuId = Double.valueOf(String.valueOf(map.get("skuId")));

        GunsProProduct prod = baseMapper.selectById(productId);
        if(prod.getIsSku() == 1){
            if(skuId == null) {
                throw new ServiceException(500, "skuId为空，无法扣减库存");
            }
            //开启规格
            GunsProSku sku = skuService.getOne(new QueryWrapper<GunsProSku>()
                    .eq("product_id", prod.getId())
                    .eq("id", skuId));
            if((sku.getStock() - quantity) <= 0 || (prod.getTotalStock() - quantity) <= 0){
                throw new ServiceException(500, "库存不足");
            }
            sku.setStock(sku.getStock() - quantity);
            skuService.updateById(sku);
        }
        if((prod.getTotalStock() - quantity) <= 0){
            throw new ServiceException(500, "库存不足");
        }
        prod.setTotalStock(prod.getTotalStock() - quantity);
        prod.setSalesVolume(prod.getSalesVolume() + quantity);
        baseMapper.updateById(prod);
        return true;
    }

    @Override
    public HomeResult home(HomeParam param){
        HomeResult result = new HomeResult();

        //1.公告
        ApiAnnouncementResult announcementResult = remoteSystemService.selectAnn();
        result.setApiAnnouncementResult(announcementResult);


        //2.用户信息*
        GunsMemParamResult mem = remoteMemberService.getMem(param.getMemberId());
        MemberInfoResult memberInfoResult=new MemberInfoResult();
        memberInfoResult.setPhoto(mem.getPhoto());
        memberInfoResult.setNickName(mem.getNickName());
        result.setMemberInfoResult(memberInfoResult);

        //3.分组*
        List<GroupResult> select = gunsProGroupService.select();
        result.setGroupResult(select);

        //4.广告图片*
        List<ApiAdvertisingResult> apiAdvertisingResultList = remoteSystemService.selectAdvertisingImg();
        result.setApiAdvertisingResultList(apiAdvertisingResultList);

        //5.橱窗图片*
        List<ApiShowcaseResult> apiShowcaseResultList= remoteSystemService.selectShowcaseImg();
        result.setApiShowcaseResultList(apiShowcaseResultList);

        //6.今日秒杀
        //今日秒杀商品未知 展示模拟数据
        PageResult<ProductResult> seckillToday = productService.seckillTodayProduct();
        result.setSeckillTodayResult(seckillToday);

        //7.品牌活动
        //品牌活动商品未知 展示模拟数据
        PageResult<ProductResult> brandActivity = productService.brandActivityProduct();
        result.setBrandActivityResult(brandActivity);

        //8.热销榜（根据商品销量）3个
        //热销榜商品未知 展示模拟数据
        PageResult<ProductResult> hotList = productService.hotListProduct();
        result.setHotListResult(hotList);

        //9.分类信息*
        List<ApiGunsProCategoryResult> gunsProCategoryResultList = gunsProCategoryService.OneCategoryList();
        result.setApiGunsProCategoryResults(gunsProCategoryResultList);
        //10.默认（精选商品）分类商品
        PageResult<ProductResult> productList = productService.categoryProductList(param.getCategoryId());
        result.setCategoryProductListResult(productList);
        return result;
    }


    @Override
    public PageResult<ProductResult> categoryProductList(Long categoryId) {
        if (categoryId==null){
            throw new ServiceException(500,"分类id不能为空");
        }
        PageResult<ProductResult> result = new PageResult<>();
            IPage<GunsProProduct> page = new Page<>();
            page.setSize(4);
            page.setCurrent(1);
            QueryWrapper<GunsProProduct> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("category_id",categoryId);
            page = baseMapper.selectPage(page,queryWrapper);

            result.setPage(page.getCurrent());
            result.setPageSize(page.getSize());
            result.setTotalPage(page.getPages());
            result.setTotalRows(page.getTotal());
            List<GunsProProduct> rows = page.getRecords();
            List<ProductResult> rows_result = new ArrayList<>();
            rows.forEach(item -> {
                ProductResult r = new ProductResult();
                ToolUtil.copyProperties(item, r);
                rows_result.add(r);
            });
            result.setRows(rows_result);
        return result;
    }


    //查询商品信息，用来展示模拟数据
    @Override
    public PageResult<ProductResult> product() {
        IPage<GunsProProduct> page = new Page<>();
        page.setSize(3);
        page.setCurrent(1);

        page = baseMapper.selectPage(page, null);
        PageResult<ProductResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        List<GunsProProduct> rows = page.getRecords();
        List<ProductResult> rows_result = new ArrayList<>();
        rows.forEach(item -> {
            ProductResult r = new ProductResult();
            ToolUtil.copyProperties(item, r);
            rows_result.add(r);
        });
        result.setRows(rows_result);
        return result;
    }

    //热销榜
    @Override
    public PageResult<ProductResult> hotListProduct() {
        return product();
    }


    //品牌活动
    @Override
    public PageResult<ProductResult> brandActivityProduct() {
        return product();
    }

    //今日秒杀
    @Override
    public PageResult<ProductResult> seckillTodayProduct() {
        return product();
    }

}
