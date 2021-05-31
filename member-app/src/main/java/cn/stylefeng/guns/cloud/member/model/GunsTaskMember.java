package cn.stylefeng.guns.cloud.member.model;

import java.util.Date;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("guns_task_member")
public class GunsTaskMember{

    private Long id;
    /**
     * 任务id
     */
    @TableField("task_id")
    private Long taskId;
    /**
     * 会员id
     */
    @TableField("member_id")
    private Long memberId;
    /**
     * 状态（0:未完成 1:完成）
     */
    private Integer status;
    /**
     * 领取日期
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 完成日期
     */
    @TableField("complete_date")
    private Date completeDate;

    private GunsTask task;

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

    public GunsTask getTask() {
        return task;
    }

    public void setTask(GunsTask task) {
        this.task = task;
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
