package org.mantrastore.managers;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by shaktisourav on 22/05/2017.
 */
@Stateless
public class EndOfTheDayBatch {

    @PersistenceContext
    EntityManager em;

    @Schedule(hour = "10,22", minute = "*/10", second = "*", persistent = false)
    public void runEndOfTheDay(){
        System.out.println("running every night...");
        Long count = em.createQuery("select count(o.id) from WebOrder o", Long.class).getSingleResult();

        System.out.println("Currently there are " + count + " orders in the system");
    }
}
