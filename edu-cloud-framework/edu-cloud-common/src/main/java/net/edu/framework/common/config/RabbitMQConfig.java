package net.edu.framework.common.config;

import net.edu.framework.common.page.mq.BindingName;
import net.edu.framework.common.page.mq.ExchangeName;
import net.edu.framework.common.page.mq.QueueName;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author:  马佳浩
 * date: 2022/9/3 23:22
 * version: 1.0
 */
@Configuration
public class RabbitMQConfig {


    @Bean
    TopicExchange TopicExchange() {
        return ExchangeBuilder.topicExchange(ExchangeName.DEFAULT_EXCHANGE).build();
    }

    /**
     * 队列1 默认队列：edu-queue
     *
     * @return
     */
    @Bean
    public Queue queueEdu() {
        return QueueBuilder.durable(QueueName.DEFAULT_QUEUE).build();
    }

    /**
     * 队列2 判题队列：edu-judge-queue
     *
     * @return
     */
    @Bean
    public Queue queueJudge() {
        return QueueBuilder.durable(QueueName.JUDGE_QUEUE).build();
    }


    /**
     * 绑定edu
     *
     * @return
     */
    @Bean
    Binding bindingEdu() {
        return BindingBuilder.bind(queueEdu()).to(TopicExchange()).with(BindingName.DEFAULT_BINDING);
    }

    /**
     * 绑定judge
     *
     * @return
     */
    @Bean
    Binding bindingJudge() {
        return BindingBuilder.bind(queueJudge()).to(TopicExchange()).with(BindingName.JUDGE_BINDING);
    }

}
