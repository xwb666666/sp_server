package cn.stylefeng.guns.cloud.product.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.io.UnsupportedEncodingException;

public class Callback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
        System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
        System.out.println("ConfirmCallback:     " + "原因：" + cause);
        if (ack) {
            System.out.println("消息发送确认成功");
        } else {
            System.out.println("消息发送确认失败");
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        //失败的回调
        try {
            System.out.println("ReturnCallback:     " + "消息：" + new String(message.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
        System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
        System.out.println("ReturnCallback:     " + "交换机：" + exchange);
        System.out.println("ReturnCallback:     " + "路由键：" + routingKey);

    }
}
