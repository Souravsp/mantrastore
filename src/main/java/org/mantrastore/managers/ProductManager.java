package org.mantrastore.managers;

import org.mantrastore.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by shaktisourav on 20/05/2017.
 */
@Stateless
public class ProductManager {
    @PersistenceContext
    EntityManager em;

    public List<Product> listProductsSorted(String filter, boolean asc){
//        return em.createQuery("select p from Product as p", Product.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        query.select(product);
        if (asc)
        {
            query.orderBy(cb.asc(product.get("price")));
        }
        else
        {
            query.orderBy(cb.desc(product.get("price")));
        }

        if(filter!=null){
            query.where(cb.like(product.<String>get("name"), "%"+filter + "%"));
        }

        return em.createQuery(query).getResultList();
    }

    public List<Product> listProducts(){
        return listProductsSorted(null, true);
    }

    public Product findProduct(long pid) {
        return em.find(Product.class, pid);
    }
}
