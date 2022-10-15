#

Run both the Producer and consumer 

- Producer

```java
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

public class Publisher {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");

        TextMessage textMessage = session.createTextMessage("First Message!");

        MessageProducer producer = session.createProducer(destination);
        producer.send(textMessage);

        System.out.println("Message Published !!");
        session.close();
        connection.close();
    }
}
```

- Consumer

```java
import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Text;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");

        final Connection connection = factory.createConnection();

        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
```
<img width="1167" alt="Screenshot 2022-10-16 at 1 25 27 AM" src="https://user-images.githubusercontent.com/54174687/196005427-d6931fab-44ff-4181-830c-7f5944695c2b.png">

<img width="1478" alt="Screenshot 2022-10-16 at 1 25 02 AM" src="https://user-images.githubusercontent.com/54174687/196005426-6e12ec98-73b2-438e-a0f0-8d97218b877a.png">

