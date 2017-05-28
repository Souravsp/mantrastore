package org.mantrastore.managers;

import org.mantrastore.entities.WebOrder;
import org.mantrastore.mdb.SenderBean;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Created by shaktisourav on 22/05/2017.
 */
@Stateless
public class OrderSmsNotifier {

    @Inject
    SenderBean sender;

    @Asynchronous
    public void notify(@Observes WebOrder webOrder){
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        sender.setMessageText("hello from Shakti: "+webOrder.getCustomer().getEmail());
        sender.sendMessage("hello from Shakti: "+webOrder.getCustomer().getEmail());
        System.out.println("order is notified to customer with email: "+webOrder.getCustomer().getEmail());
    }
}
