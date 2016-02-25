package com.resource.jms.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.utils.LoggerUtil;

public class TestSend {

	public static void main(String[] args) {
		TestSend testSend = new TestSend();
		try {
			testSend.testMySend();
		} catch (JMSException e) {
			LoggerUtil.info(TestSend.class, e.toString());
		}
	}

	public void testMySend() throws JMSException {
		Connection connection = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616")
				.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("myTest3"); // 只能消费1次
		MessageProducer producer = session.createProducer(queue);
		Message msg = session.createTextMessage("hello world");
		producer.send(msg);
		producer.close();
		session.close();
		connection.close();
	}
}
