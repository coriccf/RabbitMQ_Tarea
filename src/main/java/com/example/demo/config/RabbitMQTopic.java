package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopic {

    public static final String EXCHANGE_TOPIC = "exchange_topic";
    public static final String ROUTING_STUDENT = "routing_Student";
    @Bean
    Queue queueStudentTopic(){
        return new Queue("queue.Student", false);
    }

    @Bean
    TopicExchange exchangeTopic(){
        return new TopicExchange(EXCHANGE_TOPIC);
    }

    @Bean
    Binding bindingStudentTopic(Queue queueStudent, TopicExchange exchange){
        return BindingBuilder.bind(queueStudent)
                .to(exchange)
                .with(ROUTING_STUDENT);
    }
   // @Bean
   // public Binding topicBinding2() {
    //    return BindingBuilder.bind(topicQueue2())
    //    .to(topicExchage())
    //    .with("topic.#");
   // }


    @Bean
    Binding bindingAll(Queue queueStudent, TopicExchange exchange){
        return BindingBuilder.bind(queueStudent)
                .to(exchange)
                .with("routing.#");
    }

}