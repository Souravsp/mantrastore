package org.mantrastore.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by shaktisourav on 21/05/2017.
 */
@Entity
public class WebOrder {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @ManyToMany
    private List<Product> products;
    public WebOrder(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public WebOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
