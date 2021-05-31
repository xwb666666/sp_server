package cn.stylefeng.guns.cloud.system.core.mq.dto;

import lombok.Data;

/**
 * meesage传输dto
 *
 * @author fengshuonan
 * @Date 2019/9/4 20:52
 */
@Data
public class MessageDto {

    /**
     * 提示信息
     */
    private String message;

    public MessageDto() {
    }

    public MessageDto(String message) {
        this.message = message;
    }
}
