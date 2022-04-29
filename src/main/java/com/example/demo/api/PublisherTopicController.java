package com.example.demo.api;


import com.example.demo.config.RabbitMQTopic;
import com.example.demo.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class PublisherTopicController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/publisher/topic/student")
    private ResponseEntity<Student> sendMessage(@RequestBody Student student) {
        rabbitTemplate.convertAndSend(RabbitMQTopic.EXCHANGE_TOPIC, RabbitMQTopic.ROUTING_STUDENT, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
