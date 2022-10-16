package consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer2 {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();

        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());

                //sent ack to queue from consumer side,
                textMessage.acknowledge();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}