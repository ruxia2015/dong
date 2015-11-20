package jms;
/**
 * Created by rxia on 2015/11/9.
 */


        import javax.jms.JMSException;
        import javax.jms.Queue;
        import javax.jms.QueueConnection;
        import javax.jms.QueueConnectionFactory;
        import javax.jms.QueueSender;
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
public final class SimpleQueueSender {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleQueueSender.class);

    private SimpleQueueSender() {
    }

    public static void main(String[] args) {
        String queueName = "q.bizQueue";
        QueueConnectionFactory queueConnectionFactory = null;
        QueueConnection queueConnection = null;
        QueueSession queueSession = null;
        Queue queue = null;
        QueueSender queueSender = null;
        TextMessage message = null;
        final int numMsgs=10000;
        try {
            //创建链接工厂
            queueConnectionFactory=new ActiveMQConnectionFactory();
            //创建连接
            queueConnection = queueConnectionFactory.createQueueConnection();
            //创建会话
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列
            queue  = queueSession.createQueue(queueName);
            //创建消息发送者
            queueSender = queueSession.createSender(queue);
            message = queueSession.createTextMessage();
            for (int i = 0; i < numMsgs; i++) {

                String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<SOAP-ENV:Envelope\n" +
                        "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                        "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
                        "xmlns:ns1=\" http://www.chinamobile.com/PBOSS/\">\n" +
                        "<SOAP-ENV:Header>\n" +
                        "<ns1:Address>http://10.182.254.161:8080/pbossagent/pbossserv</ns1:Address>\n" +
                        "<ns1:Username>pboss</ns1:Username>\n" +
                        "<ns1:Password>pboss</ns1:Password>\n" +
                        "<ns1:BIPCode>BTP2M008</ns1:BIPCode>\n" +
                        "<ns1:BIPVer>0100</ns1:BIPVer>\n" +
                        "<ns1:ActivityCode>TTP2M008</ns1:ActivityCode>\n" +
                        "<ns1:ProcID>350665627</ns1:ProcID>\n" +
                        "<ns1:TransIDO>350665627</ns1:TransIDO>\n" +
                        "<ns1:TransIDH>null</ns1:TransIDH>\n" +
                        "<ns1:ProcessTime>20150826190201</ns1:ProcessTime>\n" +
                        "<ns1:TestFlag>0</ns1:TestFlag>\n" +
                        "<ns1:SvcConVer>0100</ns1:SvcConVer>\n" +
                        "</SOAP-ENV:Header> \n" +
                        "<SOAP-ENV:Body> \n" +
                        "<ECMemberOrderInfoRequest>\n" +
                        "    <MemInfo>\n" +
                        "        <OprSeq>0600BTP2M00820150910190201631554</OprSeq>\n" +
                        "        <RelOprSeq>1001BIP2B95620150910175542084173</RelOprSeq>\n" +
                        "        <OprTime>20150826175542</OprTime>\n" +
                        "        <OprCode>01</OprCode>\n" +
                        "        <ApplyChannel>02</ApplyChannel>\n" +
                        "        <ProdID>I00010100001</ProdID>\n" +
                        "        <ProdInstID>910000000001044114</ProdInstID>\n" +
                        "        <SubsID>910000000001016613</SubsID>\n" +
                        "        <ProdInstEffTime>20140527140852</ProdInstEffTime>\n" +
                        "        <ProdInstExpTime>20991231235959</ProdInstExpTime>\n" +
                        "        <ProdInfo>\n" +
                        "            <ProdID>I00010100035</ProdID>\n" +
                        "            <PkgProdID>I00010100027</PkgProdID>\n" +
                        "            <ProdInstID>910000000002048608</ProdInstID>\n" +
                        "            <ProdInstEffTime>20140530092820</ProdInstEffTime>\n" +
                        "            <ProdInstExpTime>20150826175444</ProdInstExpTime>\n" +
                        "            <OprType>01</OprType>\n" +
                        "            <ProdAttrInfo>\n" +
                        "                <ServiceID>S00010100035</ServiceID>\n" +
                        "                <AttrKey>APNNAME</AttrKey>\n" +
                        "                <AttrValue>CMMTM</AttrValue>\n" +
                        "                <OprType>01</OprType>\n" +
                        "            </ProdAttrInfo>\n" +
                        "            <SubsID>910000000001016613</SubsID>\n" +
                        "        </ProdInfo>\n" +
                        "        <ProdInfo>\n" +
                        "            <ProdID>I00010100035</ProdID>\n" +
                        "            <PkgProdID>I00010100027</PkgProdID>\n" +
                        "            <ProdInstID>910000000002048709</ProdInstID>\n" +
                        "            <ProdInstEffTime>20140530092820</ProdInstEffTime>\n" +
                        "            <ProdInstExpTime>20160826175444</ProdInstExpTime>\n" +
                        "            <OprType>01</OprType>\n" +
                        "            <ProdAttrInfo>\n" +
                        "                <ServiceID>S00010100030</ServiceID>\n" +
                        "                <AttrKey>APNNAME</AttrKey>\n" +
                        "                <AttrValue>CMMOT</AttrValue>\n" +
                        "                <OprType>01</OprType>\n" +
                        "            </ProdAttrInfo>\n" +
                        "            <SubsID>910000000000009288</SubsID>\n" +
                        "        </ProdInfo>\n" +
                        "        <CustomerNumber>100015430100100000</CustomerNumber>\n" +
                        "        <ECProdInstID>910000000001047586</ECProdInstID>\n" +
                        "        <ECSubsID>910000000000261489</ECSubsID>\n" +
                        "        <ProvinceID>100</ProvinceID>\n" +
                        "    </MemInfo>\n" +
                        "</ECMemberOrderInfoRequest>\n" +
                        "</SOAP-ENV:Body> \n" +
                        "</SOAP-ENV:Envelope>";


                message.setText(str);
                LOG.info("Sending message:  --------{}"  ,i);
                queueSender.send(message);
            }

            //发送消息
            queueSender.send(queueSession.createMessage());
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
