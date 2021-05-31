package cn.stylefeng.guns.cloud.member.constants;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-05-08 13:35
 **/
public class RechargeWithdrawalConstant {

    public enum RechargeWithdrawalStatus {

        NO_STATUS(0, "未完成"),
        STATUS(1, "已完成");

        private RechargeWithdrawalStatus(Integer value, String text) {
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

    public enum RechargeWithdrawalType {

        RECHARGE(0, "充值"),
        WITHDRAWAL(1, "提现");

        private RechargeWithdrawalType(Integer value, String text) {
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

}
