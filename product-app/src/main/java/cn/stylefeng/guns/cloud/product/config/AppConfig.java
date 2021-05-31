package cn.stylefeng.guns.cloud.product.config;

import cn.stylefeng.guns.cloud.libs.config.*;
import cn.stylefeng.guns.cloud.libs.web.error.DefaultExceptionHandler;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {DataSourceConfig.class,
        CustomFastJsonConfig.class,
        MybatisPluginConfig.class,
        RedisConfig.class,
        RibbonConfig.class,
        ScannerConfig.class,
        SecurityConfiguration.class,
        SentinelConfig.class})
@Configuration
public class AppConfig {

    @Value("${elasticsearch.host}")
    private String es_host;
    @Value("${elasticsearch.port}")
    private Integer es_port;

    /**
     * 默认的全局异常拦截器
     */
    @Bean
    public DefaultExceptionHandler defaultControllerExceptionHandler() {
        return new DefaultExceptionHandler();
    }

    /**
     * es 通用请求头
     */
    public static RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient esRestClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(es_host, es_port, "http")
                )
        );
        return client;
    }
//    @Bean
//    @Scope("prototype")
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMandatory(true);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }
    /**
     * 使用SimpleMessageListenerContainer容器设置消费队列监听
     * 不使用@RabbitListener注解
     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
//        simpleMessageListenerContainer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//
//            }
//        });
//        //simpleMessageListenerContainer.setQueueNames("","");
//        //impleMessageListenerContainer.addQueueNames();
//        //手动确认
//        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return simpleMessageListenerContainer;
//    }

    /**
     * @return
     * @RabbitListener注解指定目标方法来作为消费消息的方法，通过注解参数指定所监听的队列或者Binding。使用@RabbitListener可以设置一个自己明确默认值的RabbitListenerContainerFactory对象。
     */
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory =
                new SimpleRabbitListenerContainerFactory();
        //这个connectionFactory就是我们自己配置的连接工厂直接注入进来
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        //这边设置消息确认方式由自动确认变为手动确认
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消息预取数量
//        simpleRabbitListenerContainerFactory.setPrefetchCount(1);
        return simpleRabbitListenerContainerFactory;
    }

    //如果是单例的

    /**
     * 每个rabbitTemplate方法只可以有一个回调，不然会报错 only one ConfirmCallback is supported by each RabbitTemplate，解决办法是配成多利的
     *
     * @param connectionFactory
     * @return
     */
    @Bean
//    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //成功回调
        template.setConfirmCallback(new Callback());
        // 开启mandatory模式（开启失败回调）
        template.setMandatory(true);
        //失败回调
        template.setReturnCallback(new Callback());

        return template;
    }
}
