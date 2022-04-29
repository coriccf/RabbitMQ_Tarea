package com.example.demo.api;

import com.example.demo.config.RabbitMQFanout;
import com.example.demo.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class PublisherFanoutController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/publisher/fanout/student")
    private ResponseEntity<Student> sendMessage(@RequestBody Student student) {
        rabbitTemplate.convertAndSend(RabbitMQFanout.EXCHANGE_FANOUT, "", student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
