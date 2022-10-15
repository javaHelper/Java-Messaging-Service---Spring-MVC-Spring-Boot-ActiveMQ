package com.demo.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class Publisher {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");

        TextMessage textMessage = session.createTextMessage("Second Message!");

        MessageProducer producer = session.createProducer(destination);
        producer.send(textMessage);

        System.out.println("Message Published !!");
        session.close();
        connection.close();
    }
}