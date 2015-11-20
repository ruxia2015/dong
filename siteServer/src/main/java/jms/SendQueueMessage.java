package jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * Created by rxia on 2015/11/5.
 */
public class SendQueueMessage {
    private JmsTemplate jmsTemplate;
    private String topicName;
    private Queue queue;

    public void sendMessage(final String message) {
        try {
            if (queue == null) {
                queue = jmsTemplate.getConnectionFactory().createConnection()
                        .createSession(false, Session.AUTO_ACKNOWLEDGE)
                        .createQueue(topicName);
            }

            jmsTemplate.send(queue, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(message);
                    return textMessage;
                }
            });
        } catch (Exception e) {

        }

    }


    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }


}
