package cn.stylefeng.guns.cloud.product.mq.consumer;

import cn.stylefeng.guns.cloud.libs.config.RabbitConfig;
import cn.stylefeng.guns.cloud.product.service.GunsProductService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class OrderConsumber /*implements ChannelAwareMessageListener*/{

    @Resource
    private GunsProductService productService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.TOPIC_QUEUE_NAME,durable = "true"),
            exchange = @Exchange(name= RabbitConfig.STOCK_EXCHANGE_NAME,durable = "true",type = "topic"),
            key = "product.stock"
        )
    )
    @RabbitHandler
    public void onStockMessage(@Payload Map<String, Double> messageBody, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        log.info("---------收到消息，开始消费---------");
        //Map<String, Object> body = (Map<String, Object>)((Message) messageBody).getBody().toString();
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            boolean result = productService.editProductStock(messageBody);
            if(result){
                //ACK,确认一条消息已经被消费。不然的话，在rabbitmq会有Unacked显示为1.
                channel.basicAck(deliveryTag, false);
                log.info("消息详情："+messageBody+" ; Ack消费结果："+result);
            } else {
                channel.basicReject(deliveryTag, true);
                log.info("消息详情："+messageBody+" ; Reject消费结果："+result);
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error("消息详情："+messageBody+" ; 消费结果：未消费");
            // TODO loop3 结束投递，进入死信队列，待做状态变更 b1:true 消息重新返回队列
            channel.basicNack(deliveryTag,false, true);
        }

    }
}