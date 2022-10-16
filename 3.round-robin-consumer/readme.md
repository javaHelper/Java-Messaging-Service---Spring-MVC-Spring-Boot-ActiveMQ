# 

- Start the consumer first

- Consumer-1

```java
public class Consumer {
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
```


- Consumer-2

```java
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
```

- Producer

```java
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
```

<img width="1350" alt="Screenshot 2022-10-16 at 5 28 33 PM" src="https://user-images.githubusercontent.com/54174687/196034207-fdb02e4e-e23e-4b45-bc51-757fdebcae48.png">

<img width="1362" alt="Screenshot 2022-10-16 at 5 28 54 PM" src="https://user-images.githubusercontent.com/54174687/196034211-a3190222-24b7-4351-bbab-e1e827cc5b1d.png">

<img width="1355" alt="Screenshot 2022-10-16 at 5 29 18 PM" src="https://user-images.githubusercontent.com/54174687/196034212-4363bbcc-c3af-4e2a-b21d-9914f6926e3e.png">
