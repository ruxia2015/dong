package jms;

/**
 * Created by rxia on 2015/11/6.
 */
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * <b>function:</b> 消息发送者
 * @author hoojo
 * @createDate 2013-6-19 上午11:26:43
 * @file MessageSender.java
 * @package com.hoo.mq.jms
 * @project ActiveMQ-5.8
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class MessageSender {

    // 发送次数
    public static final int SEND_NUM = 200;
    // tcp 地址
    public static final String BROKER_URL = "tcp://localhost:61616";
    // 目标，在ActiveMQ管理员控制台创建 http://localhost:8161/admin/queues.jsp
    public static final String DESTINATION = "q.orderSyncQueue";

    /**
     * <b>function:</b> 发送消息
     * @author hoojo
     * @createDate 2013-6-19 下午12:05:42
     * @param session
     * @param producer
     * @throws Exception
     */
    public static void sendMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 0; i < SEND_NUM; i++) {
            String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<InterBOSS>\n" +
                    "  <OrigDomain>BOSS</OrigDomain>\n" +
                    "  <HomeDomain>IOMP</HomeDomain>\n" +
                    "  <BIPCode>BIP2B964</BIPCode>\n" +
                    "  <BIPVer>0100</BIPVer>\n" +
                    "  <ActivityCode>T2140964</ActivityCode>\n" +
                    "  <ActionCode>0</ActionCode>\n" +
                    "  <Routing>\n" +
                    "    <RouteType>00</RouteType>\n" +
                    "    <RouteValue>000</RouteValue>\n" +
                    "  </Routing>\n" +
                    "  <ProcID>34323449</ProcID>\n" +
                    "  <TransIDO>74643215</TransIDO>\n" +
                    "  <TransIDH></TransIDH>\n" +
                    "  <ProcessTime>20130507171508</ProcessTime>\n" +
                    "  <SPReserve>\n" +
                    "    <TransIDC>7910BOSS74643215IOMP</TransIDC>\n" +
                    "    <CutOffDay>20130507</CutOffDay>\n" +
                    "    <OSNDUNS>7910</OSNDUNS>\n" +
                    "    <HSNDUNS>0000</HSNDUNS>\n" +
                    "    <ConvID>BOSS791067917464321520130507171619195</ConvID>\n" +
                    "  </SPReserve>\n" +
                    "  <TestFlag>0</TestFlag>\n" +
                    "  <MsgSender>0000</MsgSender>\n" +
                    "  <MsgReceiver>0634</MsgReceiver>\n" +
                    "  <SvcContVer>0100</SvcContVer>\n" +
                    "  <SvcCont><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<StatChgNtfyReq>\n" +
                    "    <CrtT>20130507171534</CrtT>\n" +
                    "    <OprSeq>7911BIP2B96420130507170542000001</OprSeq>\n" +
                    "    <ChgUserSum>1</ChgUserSum>\n" +
                    "    <ChgUser>\n" +
                    "        <IDType>01</IDType>\n" +
                    "        <IDValue>1064823000001</IDValue>\n" +
                    "        <UserStatus>02</UserStatus>\n" +
                    "        <StatusChgTime>20130507171742</StatusChgTime>\n" +
                    "        <StatusOprTime>20130507171742</StatusOprTime>\n" +
                    "        <BillingStartTime>20130507171742</BillingStartTime>\n" +
                    "        <ProvincdID>791</ProvincdID>\n" +
                    "        <SMSPNo>1111</SMSPNo>\n" +
                    "        <OprTime>20130507171742</OprTime>\n" +
                    "<OtherStatus>01</OtherStatus>\n" +
                    "    </ChgUser>\n" +
                    "</StatChgNtfyReq>]]></SvcCont>\n" +
                    "</InterBOSS>";
            TextMessage text = session.createTextMessage(message);

            System.out.println(message);
            producer.send(text);
            session.commit();
        }
    }

    public static void run() throws Exception {

        Connection connection = null;
        Session session = null;
        try {
            // 创建链接工厂
            ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            // 通过工厂创建一个连接
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(DESTINATION);
            // 创建消息制作者
            MessageProducer producer = session.createProducer(destination);
            // 设置持久化模式
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session, producer);
            // 提交会话
           // session.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭释放资源
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MessageSender.run();
    }
}