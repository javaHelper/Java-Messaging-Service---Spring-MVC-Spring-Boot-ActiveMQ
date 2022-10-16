package com.example.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createTopic("Demo-Topic");

        MessageProducer producer = session.createProducer(destination);

        TextMessage textMessage = session.createTextMessage("Message For Topic");
        producer.send(textMessage);
        System.out.println("Message Published to topic");

        session.close();
        connection.close();
    }
}