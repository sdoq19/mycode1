package priv.resource.rabbitmq;

import com.rabbitmq.client.*;
import com.sun.xml.internal.messaging.saaj.soap.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    private final static String QUEUE_NAME = "MyQueue";

    public static void main(String[] args) {
        receive();
    }

    public static void receive()
    {
        ConnectionFactory factory    = null;
        Connection        connection = null;
        Channel           channel    = null;

        try {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel){
                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                                           byte[] body) throws IOException {
                    System.out.println("11111111111");
                    String message = new String(body, "UTF-8");
                    System.out.println("收到消息....."+message);
                }};
            channel.basicConsume(QUEUE_NAME, true,consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally{
            try {
                //关闭资源
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}