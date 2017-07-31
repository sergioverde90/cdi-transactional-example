package com.sergio.examples.control;

import com.sergio.examples.entity.CdiEntity;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sergio on 26/02/2017.
 */
public class CdiControl {

    EntityManager em;

    Event<CdiEntity> entityEvent;

    @Inject
    public CdiControl(Event<CdiEntity> entityEvent, EntityManager em) {
        this.entityEvent = entityEvent;
        this.em = em;
    }

    /**
     * mark method as {@link Transactional} to create a transaction.
     * This annotations is defined in JTA spec (JSR-907).
     *
     * @param entity to persist
     *
     * @see Transactional
     */
    @Transactional
    public CdiEntity tx(CdiEntity entity){
        CdiEntity detach = em.merge(entity);
        em.persist(detach);
        entityEvent.fire(entity);
        return detach;
    }

    /**
     * CDI Event transactional are defined in CDI spec 10.4.5 (JSR-346)
     *
     * @param entity associated to event
     *
     * @see Observes
     * @see TransactionPhase
     */
    void afterSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) CdiEntity entity){
        System.err.print("transaction completed!");
        System.err.println(entity);
    }

    public List<CdiEntity> getAll() {
        TypedQuery<CdiEntity> query = em.createNamedQuery(CdiEntity.NAMED_QUERY_GET_ALL, CdiEntity.class);
        return query.getResultList();
    }
}
