//package cn.stylefeng.guns.cloud.system.core.mq.listener;
//
//import cn.stylefeng.guns.cloud.system.core.mq.dto.MessageDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.stereotype.Service;
//
///**
// * rocketmq的消费者
// *
// * @author fengshuonan
// * @Date 2019/9/4 20:56
// */
//@Service
//@Slf4j
//public class RocketMqStreamListener {
//
//    @StreamListener(Sink.INPUT)
//    public void receive(MessageDto messageDto) {
//        log.info("收到消息：" + messageDto);
//    }
//
//}
