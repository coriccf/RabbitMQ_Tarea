/*package com.example.demo.api;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class ProducerController {
    //como se va enviar ese mensaje, se tienen que crear esa clase que reciba el mensaje
    //Se va a crear MessageDto

    //Se va apoder crear las colas
    @Autowired
    private RabbitTemplate template;


    @PostMapping("/v1/api/consumer")
    public String sendMessage(@RequestBody MessageDto messageDto){
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        //con esta configuracion nosotros enviamos mensaje en las colas
        template.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, messageDto);
        return "Mensaje enviado";
    }
}
*/