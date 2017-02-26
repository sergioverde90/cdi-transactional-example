package com.sergio.examples.control;

import com.sergio.examples.entity.CdiEntity;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by Sergio on 26/02/2017.
 */
public class CdiControl {

    @PersistenceContext(unitName = "tx-cdi")
    EntityManager em;

    @Inject
    Event<CdiEntity> entityEvent;

    /**
     * mark method as {@link Transactional} to create a transaction.
     * This annotations is defined in JTA spec (JSR-907).
     *
     * @param entity
     * @see Transactional
     */
    @Transactional
    public void tx(CdiEntity entity){
        CdiEntity detach = em.merge(entity);
        em.persist(detach);
        entityEvent.fire(entity);
    }

    /**
     * CDI Event transactional are defined in CDI spec 10.4.5 (JSR-346)
     *
     * @param entity
     * @see Observes
     * @see TransactionPhase
     */
    void afterSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) CdiEntity entity){
        System.err.print("transaction completed!");
        System.err.println(entity);
    }
}
