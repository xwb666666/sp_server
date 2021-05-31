package cn.stylefeng.guns.cloud.system.core.constant;

public class Constant {

    public enum CloudService{
        //0：七牛  1：阿里云  2：本地上传 3:腾讯云
        QINIU(0),
        ALIYUN(1),
        LOCAL(2),
        QCLOUD(3);

        private int value;

        CloudService(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
    }
}
