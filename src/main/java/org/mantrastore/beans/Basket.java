package org.mantrastore.beans;

import org.mantrastore.entities.Customer;
import org.mantrastore.entities.Product;
import org.mantrastore.entities.WebOrder;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaktisourav on 21/05/2017.
 */

@Named
@ConversationScoped
public class Basket implements Serializable{
    private List<Product> productsInBasket = new ArrayList<Product>();
    private Customer customer = new Customer();
    @Inject
    Event<WebOrder> webOrderEvent;

    @Inject
    Conversation conversation;

    public void addProduct(Product product){
        if(conversation.isTransient())
            conversation.begin();
        productsInBasket.add(product);
    }

    public List<Product> getProductsInBasket(){
        return productsInBasket;
    }

    public BigDecimal getTotal(){
        BigDecimal total=new BigDecimal(0);
        for(Product product: productsInBasket)
            total = total.add(product.getPrice());
        return total;
    }

    public void setProductsInBasket(List<Product> productsInBasket) {
        this.productsInBasket = productsInBasket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String checkout()
    {
        webOrderEvent.fire(new WebOrder(customer, productsInBasket));
        if(!conversation.isTransient())
        {
            conversation.end();
        }
        return "bye";
    }
}
