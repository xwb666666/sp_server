package cn.stylefeng.guns.cloud.payment.constant;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-05-08 13:35
 **/
public class PaymentConstant {

    public enum OrderStatus {

        NO_PAY(0, "未支付"),
        ALREADY_PAY(1, "已支付");

        private OrderStatus(Integer value, String text) {
            this.text = text;
            this.value = value;
        }

        private Integer value;
        private String text;

        public Integer getValue() {
            return this.value;
        }

        public String getText() {
            return this.text;
        }
    }

    public enum NotifyStatus {
        NO_NOTIFY(0, "未发送"),
        ALREADY_NOTIFY(1, "已发送");

        public Integer getValue() {
            return value;
        }

        public String getText() {
            return text;
        }

        private Integer value;
        private String text;

        private NotifyStatus(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
    }

    public enum PayTypeEnum{
        AliPay,WxPay;
    }
}
