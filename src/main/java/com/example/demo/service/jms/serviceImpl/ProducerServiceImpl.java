package com.example.demo.service.jms.serviceImpl;


import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.service.jms.ProducerService;

@Service("producerService")
public class ProducerServiceImpl implements ProducerService{

	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

	@Override
	public void sendMessage(Destination destination, String msg) {
		
		this.jmsMessagingTemplate.convertAndSend(destination, msg);
	}
}
