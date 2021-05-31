package cn.stylefeng.guns.cloud.member.model.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

@ApiModel("任务")
public class TaskParam {

    private Long id;
    private Long memberId;
    private String taskName;
    /**
     * 任务类型（1:视频任务 2:广告任务）
     */
    private Integer taskType;
    /**
     * 奖励（如：10金豆）
     */
    private Integer reward;
    /**
     * 任务链接
     */
    private String url;
    /**
     * 任务图片
     */
    private String pic;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（1:正常 2:停止）
     */
    @ApiModelProperty("状态（1:正常 2:停止）")
    private Integer status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 截止日期
     */
    private Date endDate;

    private List<TaskMemberParam> taskMembers;

    private Long page=1L;
    private Long pageSize=20L;



    public Long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<TaskMemberParam> getTaskMembers() {
        return taskMembers;
    }

    public void setTaskMembers(List<TaskMemberParam> taskMembers) {
        this.taskMembers = taskMembers;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
        return "GunsTask{" +
        ", id=" + id +
        ", taskName=" + taskName +
        ", taskType=" + taskType +
        ", reward=" + reward +
        ", url=" + url +
        ", pic=" + pic +
        ", sort=" + sort +
        ", status=" + status +
        ", memo=" + memo +
        ", createDate=" + createDate +
        ", endDate=" + endDate +
        "}";
    }
}
