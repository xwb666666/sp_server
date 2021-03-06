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
     * ????????????????????????????????????
     *
     * @param skuNameItems
     * @return
     */
    private List<GunsProSkuName> getSkuNameInfos(List<GunsProSkuNameItem> skuNameItems) {
        List<GunsProSkuName> result = new ArrayList<>();
        if (skuNameItems != null && skuNameItems.size() > 0) {
            skuNameItems.forEach(item -> {
                //??????
                GunsProSkuName skuNameEntity = gunsProSkuNameService.saveOrGetSkuName(item.getName(), 0l);
                result.add(skuNameEntity);
                //??????
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
        ToolUtil.copyProperties(param, productBase); //???????????????
        productBase.setCreateDate(createTime);
        baseMapper.insert(productBase); //????????????
        if (param.getIsSku() == 1) //???????????????
        {
            //??????sku ??????
            List<GunsProSkuParam> gunsProSkuParams = param.getSkuItems();
            //?????????????????????????????????????????????
            List<GunsProSkuName> skuNamesEntitys = getSkuNameInfos(param.getSkuNameItems());

            if (gunsProSkuParams != null && gunsProSkuParams.size() > 0) {
                List<GunsProSku> skuEntities = gunsProSkuParams.stream().map(item -> {
                    GunsProSku skuEntity = new GunsProSku();
                    ToolUtil.copyProperties(item, skuEntity);
                    List<SkuAndSkuNameItem> skuNameItems = item.getSkuNames();

                    if (skuNameItems == null)
                        throw new RequestEmptyException("sku????????????skuname????????????");

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
                //???????????????json?????????????????????????????????
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
                baseMapper.updateById(productBase); //??????
                //????????????sku??????
                skuService.saveBatch(skuEntities);
            }
        }
        return true;
    }


    @Override
    public boolean deleteById(Long id) {

        GunsProProduct entity = baseMapper.selectById(id);
        if (entity == null)
            throw new ServiceException(500, "???????????????");
        if (entity.getStatus() == 2)
            throw new ServiceException(500, "??????????????????????????????");
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
            throw new ServiceException(500, "???????????????");
        if (entity.getStatus() != 0)
            throw new ServiceException(500, "??????????????????????????????????????????");

        QueryWrapper<GunsProSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        List<Long> skuList = skuService.list(queryWrapper).stream().map(item -> item.getId()).collect(Collectors.toList());
        skuService.removeByIds(skuList);
        this.removeById(productId);
        return true;
    }

    /**
     * ??????????????????
     *
     * @param id
     * @return
     */
    @Override
    public GunsProProductResult detail(Long id) {
        GunsProProductResult result = new GunsProProductResult();
        //1?????????????????????
        GunsProProduct entity = baseMapper.selectById(id);

        if (entity == null)
            throw new ServiceException(500, "??????????????????id");

        ToolUtil.copyProperties(entity, result);

        result.setSkuInfo(JSONObject.parseArray(entity.getSkuInfo(), ProductSkuNameItem.class));
        //??????id????????????sku ??????
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
            throw new RequestEmptyException("??????ID????????????");
        //1?????????????????????
        GunsProProduct entity = baseMapper.selectById(param.getId());
        if (entity == null)
            throw new ServiceException(500, "???????????????????????????");

        ToolUtil.copyProperties(param, entity); //???????????????
        entity.setUpdateDate(now); //??????????????????
        //baseMapper.updateById(entity); //????????????

        if (param.getIsSku() == 1d) //???????????????
        {
            //??????sku ??????
            List<GunsProSkuParam> gunsProSkuParams = param.getSkuItems();
            //?????????????????????????????????????????????
            List<GunsProSkuName> skuNamesEntitys = getSkuNameInfos(param.getSkuNameItems());

            if (gunsProSkuParams != null && gunsProSkuParams.size() > 0) {
                List<GunsProSku> skuEntities = gunsProSkuParams.stream().map(item -> {
                    GunsProSku skuEntity = new GunsProSku();
                    ToolUtil.copyProperties(item, skuEntity);
                    List<SkuAndSkuNameItem> skuNameItems = item.getSkuNames();

                    if (skuNameItems == null)
                        throw new RequestEmptyException("sku????????????skuname????????????");

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
                //???????????????json?????????????????????????????????
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
                baseMapper.updateById(entity); //??????
                //????????????sku??????
                List<GunsProSku> addEntities = skuEntities.stream().filter(n -> n.getId() == 0 || n.getId() == null).collect(Collectors.toList());
                skuService.saveBatch(addEntities);
                List<GunsProSku> updateEntities = skuEntities.stream().filter(n -> n.getId() > 0).collect(Collectors.toList());
                skuService.updateBatchById(updateEntities); //??????
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
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder(); //??????dsl??????
        BoolQueryBuilder boolQuery= QueryBuilders.boolQuery();
        //???????????????
        if(!StringUtils.isEmpty(param.getKeywords()))
        {
            boolQuery.must(QueryBuilders.matchQuery("name",param.getKeywords()));
        }
        //????????????
        if(param.getBrandId()!=null)
        {
            boolQuery.filter(QueryBuilders.termQuery("brand_id",param.getBrandId()));
        }
        //????????????
        if(param.getCateId()!=null)
        {
            boolQuery.filter(QueryBuilders.termQuery("category_id",param.getCateId()));
        }

        if(param.getSort()==1) //??????????????????????????????
        {
            sourceBuilder.sort("create_time",param.getSortType()==1? SortOrder.DESC:SortOrder.ASC);
        }

        //????????????
        sourceBuilder.sort("present_price",param.getSaleSortType()==0?SortOrder.ASC:SortOrder.DESC);

        //??????
        sourceBuilder.from((param.getPage()-1)*param.getPageSize());
        sourceBuilder.size(param.getPageSize());

        sourceBuilder.query(boolQuery);
        SearchRequest searchRequest=new SearchRequest(new String[]{mall_index},sourceBuilder);
        return searchRequest;
    }

    /**
     * ????????????
     *
     * @param id ???????????????id
     * @return
     */
    @Override
    public boolean upToSale(Long id) throws IOException {
        GunsProProduct product = baseMapper.selectById(id);
        if (product == null)
            throw new ServiceException(500, "??????????????????");
        if (product.getStatus() == 0)
            throw new ServiceException(500, "??????????????????");
        if (product.getStatus() == 2)
            throw new ServiceException(500, "??????????????????,??????????????????");

        /**
         * 1??????????????????es??????????????????(??????)
         * 2????????????????????????????????????
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
        //??????sku??????

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
        baseMapper.updateById(product); //??????
        return true;
    }

    /**
     * ??????????????????
     *
     * @param id
     * @return
     */
    @Override
    public boolean downFromSale(Long id) throws IOException {
        GunsProProduct product = baseMapper.selectById(id);
        if (product == null)
            throw new ServiceException(500, "??????????????????");
        if (product.getStatus() == 0)
            throw new ServiceException(500, "??????????????????");
        if (product.getStatus() == 1)
            throw new ServiceException(500, "??????????????????,??????????????????");
        /**
         * 1?????????es???????????????(??????)
         * 2????????????????????????????????????
         */
        DeleteRequest request = new DeleteRequest(mall_index);
        request.id(id.toString());
        DeleteResponse delete = restHighLevelClient.delete(request, AppConfig.COMMON_OPTIONS);
        if (delete.getResult().equals(DocWriteResponse.Result.DELETED)) {
            Date downTime = DateTime.now();
            product.setStatus(3);
            product.setLowOffDate(downTime);
            product.setUpdateDate(downTime);
            baseMapper.updateById(product); //??????
            return true;
        } else
            return false;

    }

    @Override
    public ApiProductDetailResult apiProductDetail(ApiProductDetailParam param) {

        ApiProductDetailResult result = new ApiProductDetailResult();
        //1?????????????????????
        GunsProProduct entity = baseMapper.selectById(param.getId());

        if (entity == null)
            throw new ServiceException(500, "??????????????????id");

        ToolUtil.copyProperties(entity, result);

        System.out.println("========================== skuinfo ===================");
        System.out.println(entity.getSkuInfo());
        result.setSkuNames(JSONObject.parseArray(entity.getSkuInfo(), ApiProductDetailResult.SkuNameItem.class));
        //??????id????????????sku ??????
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
     * ???????????????????????????????????????
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
     * ?????????????????????????????????
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
                throw new ServiceException(500, "skuId???????????????????????????");
            }
            //????????????
            GunsProSku sku = skuService.getOne(new QueryWrapper<GunsProSku>()
                    .eq("product_id", prod.getId())
                    .eq("id", skuId));
            if((sku.getStock() - quantity) <= 0 || (prod.getTotalStock() - quantity) <= 0){
                throw new ServiceException(500, "????????????");
            }
            sku.setStock(sku.getStock() - quantity);
            skuService.updateById(sku);
        }
        if((prod.getTotalStock() - quantity) <= 0){
            throw new ServiceException(500, "????????????");
        }
        prod.setTotalStock(prod.getTotalStock() - quantity);
        prod.setSalesVolume(prod.getSalesVolume() + quantity);
        baseMapper.updateById(prod);
        return true;
    }

    @Override
    public HomeResult home(HomeParam param){
        HomeResult result = new HomeResult();

        //1.??????
        ApiAnnouncementResult announcementResult = remoteSystemService.selectAnn();
        result.setApiAnnouncementResult(announcementResult);


        //2.????????????*
        GunsMemParamResult mem = remoteMemberService.getMem(param.getMemberId());
        MemberInfoResult memberInfoResult=new MemberInfoResult();
        memberInfoResult.setPhoto(mem.getPhoto());
        memberInfoResult.setNickName(mem.getNickName());
        result.setMemberInfoResult(memberInfoResult);

        //3.??????*
        List<GroupResult> select = gunsProGroupService.select();
        result.setGroupResult(select);

        //4.????????????*
        List<ApiAdvertisingResult> apiAdvertisingResultList = remoteSystemService.selectAdvertisingImg();
        result.setApiAdvertisingResultList(apiAdvertisingResultList);

        //5.????????????*
        List<ApiShowcaseResult> apiShowcaseResultList= remoteSystemService.selectShowcaseImg();
        result.setApiShowcaseResultList(apiShowcaseResultList);

        //6.????????????
        //???????????????????????? ??????????????????
        PageResult<ProductResult> seckillToday = productService.seckillTodayProduct();
        result.setSeckillTodayResult(seckillToday);

        //7.????????????
        //???????????????????????? ??????????????????
        PageResult<ProductResult> brandActivity = productService.brandActivityProduct();
        result.setBrandActivityResult(brandActivity);

        //8.?????????????????????????????????3???
        //????????????????????? ??????????????????
        PageResult<ProductResult> hotList = productService.hotListProduct();
        result.setHotListResult(hotList);

        //9.????????????*
        List<ApiGunsProCategoryResult> gunsProCategoryResultList = gunsProCategoryService.OneCategoryList();
        result.setApiGunsProCategoryResults(gunsProCategoryResultList);
        //10.????????????????????????????????????
        PageResult<ProductResult> productList = productService.categoryProductList(param.getCategoryId());
        result.setCategoryProductListResult(productList);
        return result;
    }


    @Override
    public PageResult<ProductResult> categoryProductList(Long categoryId) {
        if (categoryId==null){
            throw new ServiceException(500,"??????id????????????");
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


    //?????????????????????????????????????????????
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

    //?????????
    @Override
    public PageResult<ProductResult> hotListProduct() {
        return product();
    }


    //????????????
    @Override
    public PageResult<ProductResult> brandActivityProduct() {
        return product();
    }

    //????????????
    @Override
    public PageResult<ProductResult> seckillTodayProduct() {
        return product();
    }

}
