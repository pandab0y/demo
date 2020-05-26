package com.example.demo.service.jms;

import javax.jms.Destination;


public interface ProducerService {

	void sendMessage(Destination destionation, String msg);
	
	
}
