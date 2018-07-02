package priv.resource.jms.topic;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import priv.utils.LoggerUtil;

public class TestTopicPublisher {

	public static void main(String[] args) {
		TestTopicPublisher testReceive = new TestTopicPublisher();
		try {
			testReceive.testMyTopicPublisher();
		} catch (JMSException e) {
			LoggerUtil.info(TestTopicPublisher.class, e.toString());
		}
	}

	public void testMyTopicPublisher() throws JMSException {
		Connection connection = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616")
				.createConnection();
		Session session = null;
		Topic topic = null;
		MessageProducer producer = null;
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic("myTopicTest");
		producer = session.createProducer(topic);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		Message msg = session.createTextMessage("hello and whatever u say");
		producer.send(msg);
		producer.close();
		session.close();
		connection.close();
	}
}
