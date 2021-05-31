package cn.stylefeng.guns.cloud.libs.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //binding key
    public final static String STOCK = "product.stock";
    //queue name
    public final static String TOPIC_QUEUE_NAME = "product_stock";
    //exchange name
    public final static String STOCK_EXCHANGE_NAME = "stockExchange";


}