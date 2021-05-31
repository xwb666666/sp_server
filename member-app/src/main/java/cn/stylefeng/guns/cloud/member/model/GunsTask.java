package cn.stylefeng.guns.cloud.member.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

@TableName("guns_task")
public class GunsTask{

    private Long id;
    private String taskName;
    /**
     * 任务类型（1:视频任务 2:广告任务）
     */
    @TableField("task_type")
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
    private Integer status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 截止日期
     */
    @TableField("end_date")
    private Date endDate;

    @TableField(exist=false)
    private List<GunsTaskMember> taskMembers;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public List<GunsTaskMember> getTaskMembers() {
        return taskMembers;
    }

    public void setTaskMembers(List<GunsTaskMember> taskMembers) {
        this.taskMembers = taskMembers;
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
