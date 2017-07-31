package com.sergio.examples.control;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractControl<T> {

    EntityManager em;
    Class<T> type;

    @Inject
    public AbstractControl(Class<T> type, EntityManager em) {
        this.type = type;
        this.em = em;
    }

    protected T persist(T entity) {
        T detach = em.merge(entity);
        em.persist(detach);
        return detach;
    }

    protected List<T> findAll() {
        TypedQuery<T> query = em.createQuery("select c from " + type.getName()+" c", type);
        return query.getResultList();
    }

    protected T findById(Object id) {
        return em.find(type, id);
    }

}
