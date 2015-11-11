package jms;



        import javax.jms.JMSException;
        import javax.jms.Message;
        import javax.jms.Queue;
        import javax.jms.QueueConnection;
        import javax.jms.QueueConnectionFactory;
        import javax.jms.QueueReceiver;
        import javax.jms.QueueSession;
        import javax.jms.Session;
        import javax.jms.TextMessage;

        import org.apache.activemq.ActiveMQConnectionFactory;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
/**
 * ActiveMQ的Queue消息队列的
 * @author longgangbai
 *
 */
public final class SimpleQueueReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleQueueReceiver.class);

    private SimpleQueueReceiver() {
    }

    public static void main(String[] args) {
        String queueName = "activemqqueue";
        QueueConnectionFactory queueConnectionFactory = null;
        QueueConnection queueConnection = null;
        QueueSession queueSession = null;
        Queue queue = null;
        QueueReceiver queueReceiver = null;
        TextMessage message = null;
        try {
            //创建连接工厂
            queueConnectionFactory=new ActiveMQConnectionFactory();
            //创建连接
            queueConnection = queueConnectionFactory.createQueueConnection();
            //创建连接会话
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列
            queue  = queueSession.createQueue(queueName);
            //创建消息接受者
            queueReceiver = queueSession.createReceiver(queue);
            queueConnection.start();
            long lasTime = System.currentTimeMillis();
            while (true) {


                Message m = queueReceiver.receive();
                if (m != null) {
                    LOG.info("间隔时间{}ms   -   {}  " , (System.currentTimeMillis() - lasTime),(System.currentTimeMillis() - lasTime)/1000.0);
                    if (m instanceof TextMessage) {
                        message = (TextMessage)m;
                        LOG.info("Reading message: " + message.getText());
                    } else {
                        break;
                    }
                    lasTime = System.currentTimeMillis();
                }


                try {
                    long time =60* 1000;
                    Thread.sleep(time);
                    LOG.info("sleep ....{}ms" ,time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            LOG.info("Exception occurred: " + e.toString());
        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}
