package com.zhen.jms.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
*消息接收者
*/
public class Receiver{
	
	public static void main(String[] args){
		receiveMessage();
	}
	
	public static void receiveMessage(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate)ctx.getBean("jmsTemplate");
		while(true){
			String msg = (String)jmsTemplate.receiveAndConvert();
			System.out.println("接收到消息：" + msg);
		}
	}
}