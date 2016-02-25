package com.resource.jms.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.utils.LoggerUtil;

public class TestReceive {

	public static void main(String[] args) {
		TestReceive testReceive = new TestReceive();
		try {
			testReceive.testMyReceive();
		} catch (JMSException e) {
			LoggerUtil.info(TestReceive.class,  e.toString());
		}
	}

	public void testMyReceive() throws JMSException {
		Connection connection = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616")
				.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("myTest3");
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener(new MyListener());
		connection.start();
		consumer.close();
		session.close();
		connection.close();
	}
}
