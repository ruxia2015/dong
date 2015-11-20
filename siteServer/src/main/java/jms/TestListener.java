package jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;

/**
 * Created by rxia on 2015/11/6.
 */
public class TestListener implements MessageListener{
    private JmsTemplate jmsTemplate;
    private JmsTemplate jmsTemplate2;
    private String topicName;

    public TestListener(JmsTemplate jmsTemplate,String topicName,JmsTemplate jmsTemplate2) {
        this.jmsTemplate = jmsTemplate;
        this.topicName = topicName;
        this.jmsTemplate2 = jmsTemplate2;
        Topic topic;
        try{
            topic = this.jmsTemplate.getConnectionFactory().createConnection()
                    .createSession(false, Session.AUTO_ACKNOWLEDGE).createTopic(this.topicName);
            DefaultMessageListenerContainer dmc = new DefaultMessageListenerContainer();
            dmc.setPubSubDomain(false);
            dmc.setDestination(topic);
            dmc.setConnectionFactory(this.jmsTemplate.getConnectionFactory());
            dmc.setPubSubNoLocal(true);
            dmc.setPubSubNoLocal(false);

            dmc.setMessageListener(this);
            dmc.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
            dmc.initialize();
            dmc.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("==========================================");
        System.out.println(message);
        System.out.println("==========================================");

    }


}
