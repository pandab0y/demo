package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.jms.ProducerService;

@RestController
public class Producer {
	@Autowired
	private ProducerService producerService;

    @RequestMapping("/sendMsg")
    public void send(String name) {
    	List<String> alarmStrList = new ArrayList<>();
        alarmStrList.add(name+"  out fence01");
        alarmStrList.add(name+"  out fence02");
        alarmStrList.add(name+"  in fence01");
        alarmStrList.add(name+"  in fence02");
        
     // 写入消息队列
        Destination destination = new ActiveMQQueue("mytest.queue");
        for (String alarmStr : alarmStrList) {
        	producerService.sendMessage(destination, alarmStr);
        }
    }

}
