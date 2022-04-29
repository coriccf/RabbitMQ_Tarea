package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanout {
    public static final String EXCHANGE_FANOUT = "exchange_fanout";
    public static final String ROUTING_STUDENT = "routing_Student";
    @Bean
    Queue queueStudentFanout(){
        return new Queue("queue.Student", false);
    }

    @Bean
    FanoutExchange exchangeFanout(){
        return new FanoutExchange(EXCHANGE_FANOUT);
    }

/*
    FanoutRabbitConfig.bindingExchangeB(...)
    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage)
        .to(fanoutExchange);
    }
*/
    @Bean
    Binding bindingStudentFanout(Queue queueStudent, FanoutExchange exchange){
        return BindingBuilder.bind(queueStudent)
                .to(exchange);
    }
    //como todos los mensajes de las colas llegaN en bytes, tenemos que procesarlo
    @Bean
    public MessageConverter messageConverterFanout() {
        return new Jackson2JsonMessageConverter();
    }
    //Para que el proyecto pueda conectarse con el rabbit mq
    //Se hizo la conection con el connection factory
    @Bean
    public AmqpTemplate templateFanout(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverterFanout());
        return rabbitTemplate;
    }
}