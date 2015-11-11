package jms.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.TextMessage;


public class JMSConsumerTest {
    private static final Logger LOG = LoggerFactory.getLogger(JMSConsumerTest.class);

    public static void main(String[] args) throws Exception {

        //**  JMSConsumer 可以设置成全局的静态变量，只需实例化一次即可使用,禁止循环重复实例化JMSConsumer(因为其内部存在一个线程池)

        JMSConsumer consumer = new JMSConsumer();
        consumer.setBrokerUrl("tcp://localhost:61616");
        consumer.setQueue("activemqqueue");
        // consumer.setUserName("system");
        //  consumer.setPassword("manager");
        consumer.setQueuePrefetch(20);
        consumer.setMessageListener(new MultiThreadMessageListener(10, new MessageHandler() {
            long lasTime = System.currentTimeMillis();

            public void handle(Message m) {
                try {
                    TextMessage message = null;
                    if (m != null) {
                        LOG.info("间隔时间{}ms   -   {}  ", (System.currentTimeMillis() - lasTime), (System.currentTimeMillis() - lasTime) / 1000.0);
                        if (m instanceof TextMessage) {
                            message = (TextMessage) m;
                            LOG.info("Reading message: " + message.getText());
                        } else {
                        }
                        lasTime = System.currentTimeMillis();
                    }

                    long time = 70 * 1000;
                    LOG.info("sleep ....{}ms", time);
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));
        consumer.start();

//      Thread.sleep(5000);
//      consumer.shutdown();

    }


}