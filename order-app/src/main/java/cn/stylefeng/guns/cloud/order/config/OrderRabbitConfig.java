package cn.stylefeng.guns.cloud.order.config;

import cn.stylefeng.guns.cloud.libs.config.RabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class OrderRabbitConfig extends RabbitConfig {
    //binding key
    public final static String STOCK = "product.stock";
    //queue name
    public final static String TOPIC_QUEUE_NAME = "product_stock";
    //exchange name
    public final static String STOCK_EXCHANGE_NAME = "stockExchange";

    //public final static String routingKey = "";
 
//    @Bean
//    public Queue stockQueue() {
//        return new Queue(TOPIC_QUEUE_NAME,true,false,false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(STOCK_EXCHANGE_NAME,true,false);
//    }

    //将stockQueue和stockExchange binding key,而且绑定的键值为product.stock,通配路由键规则"product.#"
    //这样只要是消息携带的binding key是product.stock,才会分发到该队列
//    @Bean
//    Binding bindingExchangeMessage() {
//        return BindingBuilder.bind(stockQueue()).to(exchange()).with(STOCK);
//    }

}