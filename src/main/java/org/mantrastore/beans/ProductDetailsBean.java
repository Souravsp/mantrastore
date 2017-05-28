package org.mantrastore.beans;

import org.mantrastore.managers.ProductManager;
import org.mantrastore.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by shaktisourav on 20/05/2017.
 */
@Named
@RequestScoped
public class ProductDetailsBean {
    private Product product;
    private long pid;

    @Inject
    ProductManager productManager;


    public void loadProduct() {

        product = productManager.findProduct(pid);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }


}
