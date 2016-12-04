package com.zhen.jms.activemq;

import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQConnection;

public class Receiver{
	
	public static void main(String[] args){
		receiveMessage();
	}
	
	public static void receiveMessage(){
		//连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD,
			"tcp://localhost:61616");
		
		//连接
		Connection connection = null;
		//会话
		Session session = null;
		//目的
		Destination destination = null;
		//消费者
		MessageConsumer consumer = null;
		
		try{
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			while(true){
				TextMessage msg = (TextMessage)consumer.receive();
				if(null != msg){
					System.out.println("收到消息：" + msg.getText());
				}
			}
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