package cn.stylefeng.guns.cloud.member.model.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("会员的任务")
public class TaskMemberParam {

    private Long id;
    /**
     * 任务id
     */
    @ApiModelProperty("任务id")
    private Long taskId;
    /**
     * 会员id
     */
    @ApiModelProperty("会员id")
    private Long memberId;
    /**
     * 状态（0:未完成 1:完成）
     */
    @ApiModelProperty("状态（0:未完成 1:完成）")
    private Integer status;
    /**
     * 领取日期
     */
    @ApiModelProperty("领取日期")
    private Date createDate;
    /**
     * 完成日期
     */
    @ApiModelProperty("完成日期")
    private Date completeDate;

    private Long page=1L;
    private Long pageSize=20L;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "GunsTaskMember{" +
        ", id=" + id +
        ", taskId=" + taskId +
        ", memberId=" + memberId +
        ", status=" + status +
        ", createDate=" + createDate +
        ", completeDate=" + completeDate +
        "}";
    }
}
