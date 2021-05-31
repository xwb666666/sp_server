package cn.stylefeng.guns.cloud.system.modular.sys.model.params;


import lombok.Data;

@Data
public class SysFeedbackParam {

    private Long memberId;                  //会员id

    private  String questionsComments;      //问题与意见

    private String picture;                 //图片

    private String relationWay;             //联系方式
}
