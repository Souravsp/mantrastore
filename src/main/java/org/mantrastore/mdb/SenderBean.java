package org.mantrastore.mdb;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.transaction.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by shaktisourav on 22/05/2017.
 */


@JMSDestinationDefinition(
        name = "java:comp/jms/webappQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "PhysicalWebappQueue")
@Stateless
@TransactionManagement( TransactionManagementType.BEAN )

public class SenderBean {

    static final Logger logger = Logger.getLogger("SenderBean");

    @Resource
    private EJBContext ejbContext;

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(lookup = "java:comp/jms/webappQueue")
    private Queue queue;
    private String messageText;

    /**
     * Creates a new instance of SenderBean
     */
    public SenderBean() {
    }

    /**
     * Get the value of messageText
     *
     * @return the value of messageText
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Set the value of messageText
     *
     * @param messageText new value of messageText
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /*
     * Within a try-with-resources block, create context.
     * Create message.
     * Create producer and send message.
     */
    public void sendMessage(String message) {
        try {
            UserTransaction utx = ejbContext.getUserTransaction();

            try {
                utx.begin();

            String text = "Message from producer: " + message;
            System.out.println(text);
            context.createProducer().send(queue, text);
                utx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    utx.rollback();
                } catch (SystemException e1) {
                    e1.printStackTrace();
                }
            }

        } catch (JMSRuntimeException t) {
            logger.log(Level.SEVERE,
                    "SenderBean.sendMessage: Exception: {0}",
                    t.toString());
        }
    }
}
