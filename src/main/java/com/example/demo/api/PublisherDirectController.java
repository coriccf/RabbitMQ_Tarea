package com.example.demo.api;
import com.example.demo.config.RabbitMQDirect;
import com.example.demo.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublisherDirectController {


    //Se va apoder crear las colas
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/publisher/direct/student")
    private ResponseEntity<Student> sendMessage(@RequestBody Student student) {
        rabbitTemplate.convertAndSend(RabbitMQDirect.EXCHANGE_DIRECT, RabbitMQDirect.ROUTING_STUDENT_KEY, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

