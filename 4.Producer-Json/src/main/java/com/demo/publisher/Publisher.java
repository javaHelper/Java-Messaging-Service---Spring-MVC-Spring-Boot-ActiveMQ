package com.demo.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class Publisher {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");

        MessageProducer producer = session.createProducer(destination);

        String[] messages = {"First Message", "Second Message", "Third Message", "Fourth Message"};
        for (String message: messages) {
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
        }

        System.out.println("Message Published !!");
        session.close();
        connection.close();
    }
}