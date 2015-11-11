package jms;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * ActiveMQ的Queue消息队列的
 * @author longgangbai
 *
 */
public final class SimpleQueueReceiverThread implements  Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleQueueReceiverThread.class);

    private SimpleQueueReceiverThread() {
    }

    public static  void main(String[] args){

        new Thread(new SimpleQueueReceiverThread()).start();
        new Thread(new SimpleQueueReceiverThread()).start();

    }


    @Override
    public void run() {
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
                    long time =70* 1000;
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
