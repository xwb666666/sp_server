package cn.stylefeng.guns.cloud.member.constants;

public interface Constants {

    Integer ENABLE = 1;
    Integer UNENABLE = 0;

    //0金豆增加 1金豆减少
    Integer FER_CHANGE_ADD = 0;
    Integer FER_CHANGE_SUBTRACT = 1;

    public enum TaskType {
        //"1:绑定推荐人 2:邀请好友 3:签到 4:看视频 5:视频任务 6:广告任务"
        TASK_TYPE_1(0, "绑定推荐人"),
        TASK_TYPE_2(1,"邀请好友"),
        TASK_TYPE_3(2,"签到"),
        TASK_TYPE_4(3,"看视频"),
        TASK_TYPE_5(4,"视频任务"),
        TASK_TYPE_6(5,"广告任务");

        TaskType(Integer value, String name){
            this.value = value;
            this.name = name;
        }
        private Integer value;
        private String name;
        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
