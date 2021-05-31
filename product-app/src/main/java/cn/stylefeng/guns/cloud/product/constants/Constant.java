package cn.stylefeng.guns.cloud.product.constants;

public interface Constant {


    String DATE_TIME = "yyyy-MM-dd HH:mm:ss";


    /********* 会员领取优惠券领取方式，直接与非直接获取 ***********/
    int COUPON_RECORD_IS_PICK = 1;
    int COUPON_RECORD_IS_NOT_PICK = 1;

    /*********** 会员领取优惠券状态 ***********/
    int COUPON_RECORD_STATUS_PICK = 1;
    int COUPON_RECORD_STATUS_USE = 2;
    int COUPON_RECORD_STATUS_EXP = 3;

    int EXPIRE_TYPE_ = 1;   //固定日期
    int EXPIRE_TYPE = 2;    //领取后有效天数
}

