package org.mantrastore.managers;

import org.mantrastore.entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

/**
 * @author Shakti Sourav - virtualshakti@gmail.com
 */
@Singleton
@Startup
public class TestDataInserter {
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void insert() {
        for(int i =1; i <= 5; i++) {
            em.persist(new Product(i + " product", new BigDecimal(10 * i)));
        }
    }
}
