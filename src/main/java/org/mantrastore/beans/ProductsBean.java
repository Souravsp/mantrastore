package org.mantrastore.beans;

import org.mantrastore.managers.ProductManager;
import org.mantrastore.entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by shaktisourav on 20/05/2017.
 */
@Named
@RequestScoped
public class ProductsBean {
    private List<Product> products;
    private String filter;

    @Inject
    ProductManager productManager;


    boolean order = true;
    public List<Product> getProducts() {

        if(products == null)
        {
           products = productManager.listProductsSorted(filter, order);
        }
        return products;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
        products = null;
    }
}
