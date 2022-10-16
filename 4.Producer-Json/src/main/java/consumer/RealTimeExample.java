package consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class RealTimeExample {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from_date","01-Jan-2024");
        jsonObject.put("to_date","31-Dec-2024");
        jsonObject.put("email","xyz@gmail.com");
        jsonObject.put("query","select * from data");

        TextMessage textMessage = session.createTextMessage(jsonObject.toString());

        MessageProducer producer = session.createProducer(destination);
        producer.send(textMessage);
        System.out.println("Send Message !!");
        session.close();
        connection.close();
    }
}