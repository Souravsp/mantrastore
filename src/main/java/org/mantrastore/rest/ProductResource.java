package org.mantrastore.rest;

import org.mantrastore.entities.Product;
import org.mantrastore.managers.ProductManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by shaktisourav on 22/05/2017.
 */
@Path("products")
public class ProductResource {

    @Inject
    ProductManager productManager;

    @GET
    @Produces({"application/json", "application/xml"})
    public List<Product> getProducts()
    {
        return productManager.listProducts();
    }
}
