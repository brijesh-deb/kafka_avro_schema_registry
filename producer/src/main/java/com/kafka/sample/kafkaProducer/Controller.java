package com.kafka.sample.kafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class Controller {

    private final Producer producer;

    @Autowired
    public Controller(Producer producer)
    {
        this.producer = producer;
    }

    @PostMapping(value = "/publish7")
    public void sendAvroMessage7()
    {
        this.producer.sendAvroMessage7();
    }
}