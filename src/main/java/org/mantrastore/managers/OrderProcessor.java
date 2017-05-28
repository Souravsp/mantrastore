package org.mantrastore.managers;

import org.mantrastore.entities.WebOrder;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.*;

/**
 * Created by shaktisourav on 21/05/2017.
 */
@Stateless
public class OrderProcessor {

    @PersistenceContext
    EntityManager em;

    public void saveOrder(@Observes WebOrder webOrder) {
        em.persist(webOrder);
    }
}
