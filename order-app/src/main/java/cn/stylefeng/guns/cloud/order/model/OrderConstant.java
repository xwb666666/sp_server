package cn.stylefeng.guns.cloud.order.model;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-30 15:19
 **/
public  class OrderConstant {

    public  enum OrderStatus {
        //0待支付，1已支付，2已发货，3已收货，4已完成，5已取消

        New_Order(0, "待支付"),
        Paid_Order(1,"已支付"),
        Shipped_Order(2,"已发货"),
        Received_Order(3,"已收货"),
        Completed_Order(4,"已完成"),
        Cancelled_Order(5,"已取消");


        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        private Integer value;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private String desc;

        OrderStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    public enum OrderType{
        //订单类型 0普通订单 1拼团订单 2兑换订单
        Normal_Order(0,"普通订单"),
        Group_Order(1,"拼团订单"),
        Redeem_Order(2,"兑换订单");

        private Integer value;

        private String desc;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }


        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }



        OrderType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

    }

}
