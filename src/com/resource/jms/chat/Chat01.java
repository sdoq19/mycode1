package com.resource.jms.chat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

public class Chat01 implements MessageListener {

	private TopicSession pubSession;
	private TopicPublisher publisher;
	private TopicConnection connection;
	private String username;

	// 用于初始化chat的构造函数
	public Chat01(String topicFactory, String topicName, String username) throws Exception {
//		Properties env=new Properties();
//		env.put(Context.SECURITY_PRINCIPAL, "system");
//		env.put(Context.SECURITY_CREDENTIALS, "manager");
//		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
//		env.put(Context.PROVIDER_URL, "tcp://localhost:61616");

		// 使用jndi.properties文件获得一个JNDI连接
		InitialContext ctx = new InitialContext();

		// 查找一个jms连接工厂并创建连接
		TopicConnectionFactory conFactory = (TopicConnectionFactory) ctx.lookup(topicFactory);
		TopicConnection connection = conFactory.createTopicConnection();

		// 创建两个JMS会话对象
		TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		// 查找一个JMS主题
		Topic chatTopic = (Topic) ctx.lookup(topicName);

		// 创建一个JMS发布者和订阅者,createSubscriber中附加的参数是一个消息
		// 选择器(null)和noLocal标记的一个真值,它表明这个发布者生产的消息不应被他自己所消费
		TopicPublisher publisher = pubSession.createPublisher(chatTopic);
		TopicSubscriber subscriber = subSession.createSubscriber(chatTopic, null, true);

		// 设置一个JMS消息侦听器
		subscriber.setMessageListener(this);

		// 初始化Chat应用程序变量
		this.connection = connection;
		this.pubSession = pubSession;
		this.publisher = publisher;
		this.username = username;

		// 启动JMS连接，允许传递消息
		connection.start();
	}

	// 接受来自TopicSubscriber的消息
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println(textMessage.getText());

		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}
	}

	// 使用发布者创建并发送消息
	protected void writeMessage(String text) throws JMSException {
		TextMessage message = pubSession.createTextMessage();
		message.setText(username + " : " + text);
		publisher.publish(message);
	}

	// 关闭JMS连接
	public void close() throws JMSException {
		connection.close();
	}

	// 运行聊天客户端
	public static void main(String[] args) {
		try {
//			if (args.length != 3) {
//				System.out.println("Factory.Topic.or username missing");
//			}
			String topicFactory = "TopicCF";
			String topicName = "topic1";
			String username = "zhuzh1";
			Chat01 chat = new Chat01(topicFactory, topicName, username);

			// 从命令行读取
			//InputStream is1 = new ByteArrayInputStream(username.getBytes());   
			//InputStream is2 = System.in;
			//SequenceInputStream sis = new SequenceInputStream(is1, is2);
			BufferedReader commandLine = new BufferedReader(new InputStreamReader(System.in));

			// 循环
			while (true) {
				String s = commandLine.readLine();
				if (s.equalsIgnoreCase("exit")) {
					chat.close();
					System.exit(0);
				} else {
					chat.writeMessage(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

}