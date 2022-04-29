package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirect {

    public static final String EXCHANGE_DIRECT = "exchange_direct";
    public static final String ROUTING_STUDENT_KEY = "routing_Student_key";

    @Bean
    Queue queueStudent(){
        return new Queue("queue.Student", false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    Binding bindingStudent(Queue queueStudent, DirectExchange exchange){
        return BindingBuilder.bind(queueStudent)
                .to(exchange)
                .with(ROUTING_STUDENT_KEY);
    }
    //como todos los mensajes de las colas llegaN en bytes, tenemos que procesarlo
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    //Para que el proyecto pueda conectarse con el rabbit mq
    //Se hizo la conection con el connection factory
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}