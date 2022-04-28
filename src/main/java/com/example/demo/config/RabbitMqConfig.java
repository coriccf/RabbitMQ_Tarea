package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static String QUEUE = "test_queue";
    public static String EXCHANGE = "test_exchange";
    public static String ROUTING_KEY = "test_routing_key";



    //se va a crear la cola, con esta funcion solo se esta creando la cola
    //DIRECT
    @Bean
    public Queue queue()
    {
        return new Queue(QUEUE);
    }
    //Se crea el direct Exchange
    @Bean
    public DirectExchange exchange()
    {
        return new DirectExchange(EXCHANGE);
    }
    //
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange)
    {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);

    }



//como todos los mensajes de las colas llegas en bytes, tenemos que procesarlo
    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();

    }
    //Para que el proyecto pueda conectarse con el rabbit mq
    //Se hizo la conection con el connection factory
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
