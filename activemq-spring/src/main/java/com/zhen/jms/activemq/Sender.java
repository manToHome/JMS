package com.zhen.jms.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.JMSException;

/**
*消息发送者
*/
public class Sender{
	
	public static void main(String[] args){
		sendMessage();
	}
	private static void sendMessage(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate)ctx.getBean("jmsTemplate");
		
		jmsTemplate.send(new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException{
				TextMessage msg = session.createTextMessage("ActiveMQ整合Spring发送消息");
				System.out.println("发送消息：ActiveMQ整合Spring发送消息");
				return msg;
			}
		});
	}
}