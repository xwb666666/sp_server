package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class EntUserParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 用户信息id
     */
    @ApiModelProperty("用户信息id")
    private Long userId;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 用户编码
     */
    @ApiModelProperty("用户编码")
    private String userCode;

    /**
     * 姓
     */
    @ApiModelProperty("姓")
    private String lastName;

    /**
     * 名
     */
    @ApiModelProperty("名")
    private String firstName;

    /**
     * 姓（拼音）
     */
    @ApiModelProperty("姓（拼音）")
    private String lastNamePinyin;

    /**
     * 名（拼音）
     */
    @ApiModelProperty("名（拼音）")
    private String firstNamePinyin;

    /**
     * 性别(M:男   F:女)
     */
    @ApiModelProperty("性别(M:男   F:女)")
    private String sex;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private Date birthday;

    /**
     * 学历（字典项）
     */
    @ApiModelProperty("学历（字典项）")
    private String education;

    /**
     * 籍贯
     */
    @ApiModelProperty("籍贯")
    private String birthplace;

    /**
     * 民族
     */
    @ApiModelProperty("民族")
    private String nation;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idCard;

    /**
     * 婚姻状况（字典项）
     */
    @ApiModelProperty("婚姻状况（字典项）")
    private String matrimony;

    /**
     * 政治面貌（字典项）
     */
    @ApiModelProperty("政治面貌（字典项）")
    private String political;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobilePhone;

    /**
     * 排序码
     */
    @ApiModelProperty("排序码")
    private BigDecimal orderNo;

    /**
     * 状态(1:启用  2:禁用)
     */
    @ApiModelProperty("状态(1:启用  2:禁用)")
    private Integer status;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户所属部门和职务的集合
     */
    @ApiModelProperty("用户所属部门和职务的集合")
    private List<EntUserDeptParam> entUserDepts;

    @Override
    public String checkParam() {
        if (ObjectUtil.isEmpty(lastName)) {
            return "lastName为空";
        }
        if (ObjectUtil.isEmpty(firstName)) {
            return "firstName为空";
        }
        if (ObjectUtil.isEmpty(lastNamePinyin)) {
            return "lastNamePinyin为空";
        }
        if (ObjectUtil.isEmpty(firstNamePinyin)) {
            return "firstNamePinyin为空";
        }
        if (ObjectUtil.isEmpty(sex)) {
            return "sex为空";
        }
        if (ObjectUtil.isEmpty(birthday)) {
            return "birthday为空";
        }
        if (ObjectUtil.isEmpty(education)) {
            return "education为空";
        }
        if (ObjectUtil.isEmpty(birthplace)) {
            return "birthplace为空";
        }
        if (ObjectUtil.isEmpty(nation)) {
            return "nation为空";
        }
        if (ObjectUtil.isEmpty(idCard)) {
            return "idCard为空";
        }
        if (ObjectUtil.isEmpty(matrimony)) {
            return "matrimony为空";
        }
        if (ObjectUtil.isEmpty(political)) {
            return "political为空";
        }
        if (ObjectUtil.isEmpty(email)) {
            return "email为空";
        }
        if (ObjectUtil.isEmpty(phone)) {
            return "phone为空";
        }
        if (ObjectUtil.isEmpty(mobilePhone)) {
            return "mobilePhone为空";
        }
        if (ObjectUtil.isEmpty(account)) {
            return "account为空";
        }
        if (ObjectUtil.isEmpty(entUserDepts)) {
            return "请为人员配置部门和职务";
        } else {
            for (EntUserDeptParam e : entUserDepts) {
                if (ObjectUtil.isEmpty(e.getDutyId())) {
                    return "dutyId为空";
                }
                if (ObjectUtil.isEmpty(e.getDeptId())) {
                    return "deptId为空";
                }
                if (ObjectUtil.isEmpty(e.getCompanyId())) {
                    return "companyId为空";
                }
                if (ObjectUtil.isEmpty(e.getDefaultFlag())) {
                    return "defaultFlag为空";
                }
            }
        }

        return null;
    }

}
