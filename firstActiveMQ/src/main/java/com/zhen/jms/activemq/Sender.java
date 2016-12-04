package com.zhen.jms.activemq;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnection;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class Sender{
	
	private static final int MSG_NEMBER = 5;
	
	public static void main(String[] args){
		sendMessage();
	}
	
	private static void sendMessage(){
		//获取连接工厂，此处是使用ActiveMQ的连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD,
			"tcp://localhost:61616");
			
		//连接
		Connection connection = null;
		//会话
		Session session = null;
		//消息目的
		Destination destination = null;
		//消息生产者
		MessageProducer producer = null;
		
		try{
			//根据连接工厂创建连接
			connection = connectionFactory.createConnection();
			//启动连接
			//connection.start();
			//获取会话
			session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
			//获取消息目的
			destination = session.createQueue("FirstQueue");
			//创建消息生产者
			producer = session.createProducer(destination);
			
			for(int i = 0 ; i < MSG_NEMBER; i++){
				TextMessage msg = session.createTextMessage("ActiveMq 发送的消息:" + i);
				System.out.println("发送消息：ActiveMQ 发送的消息：" + i);
				producer.send(msg);
			}
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(connection != null){
				try{
					connection.close();
				}catch(Exception e){
						
					}
			}
		}	
	}
	
}