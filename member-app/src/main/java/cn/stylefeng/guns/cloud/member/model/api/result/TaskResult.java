package cn.stylefeng.guns.cloud.member.model.api.result;

import cn.stylefeng.guns.cloud.member.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

@ApiModel("任务")
public class TaskResult {

    private Long id;
    private Long memberId;
    private String taskName;
    /**
     * 任务类型（1:视频任务 2:广告任务）
     */
    private Integer taskType;
    //"绑定推荐人,邀请好友,签到,看视频,视频任务,广告任务"
    private String taskTypeDes = Constants.TaskType.TASK_TYPE_1.getName()+","+
            Constants.TaskType.TASK_TYPE_2.getName()+","+
            Constants.TaskType.TASK_TYPE_3.getName()+","+
            Constants.TaskType.TASK_TYPE_4.getName()+","+
            Constants.TaskType.TASK_TYPE_5.getName()+","+
            Constants.TaskType.TASK_TYPE_6.getName();
//    private List<Map<Integer, String>> taskTypeDes = new ArrayList<>();

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

    private List<TaskMemberResult> taskMembers;

    private Long page=1L;
    private Long pageSize=20L;



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

    public List<TaskMemberResult> getTaskMembers() {
        return taskMembers;
    }

    public void setTaskMembers(List<TaskMemberResult> taskMembers) {
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

//    public List<Map<Integer, String>> getTaskTypeDes() {
//        Map hashMap = new HashMap();
//        hashMap.put(1,"视频任务");
//        hashMap.put(2,"广告任务");
//        hashMap.put(3,"签到");
//        hashMap.put(4,"邀请好友");
//        hashMap.put(5,"看视频");
//        hashMap.put(6,"绑定推荐人");
//        this.taskTypeDes.add(hashMap);
        //1:视频任务 2:广告任务 3:绑定推荐人 4:邀请好友 5:签到 6:看视频
//        return taskTypeDes;
//    }

//    public void setTaskTypeDes(List<Map<Integer, String>> taskTypeDes) {
//        this.taskTypeDes = taskTypeDes;
//    }

    public String getTaskTypeDes() {
        return taskTypeDes;
    }

    public void setTaskTypeDes(String taskTypeDes) {
        this.taskTypeDes = taskTypeDes;
    }

    @Override
    public String toString() {
        return "GunsTask{" +
        ", id=" + id +
        ", taskType=" + taskType +
        ", taskName=" + taskName +
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
