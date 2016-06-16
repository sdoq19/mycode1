package priv.resource.jms.topic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import priv.resource.jms.queue.MyListener;
import priv.utils.LoggerUtil;

public class TestTopicConsumer {

	public static void main(String[] args) {
		TestTopicConsumer testSend = new TestTopicConsumer();
		try {
			testSend.testMyTopicConsumer();
		} catch (JMSException e) {
			LoggerUtil.info(TestTopicConsumer.class, e.toString());
		} catch (InterruptedException e) {
			LoggerUtil.info(TestTopicConsumer.class, e.toString());
		}
	}

	public void testMyTopicConsumer() throws JMSException, InterruptedException {
		Connection connection = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616")
				.createConnection();
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Topic topic = session.createTopic("myTopicTest");
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MyListener());
		connection.start();
	}
}
