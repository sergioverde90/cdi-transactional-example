package com.sergio.examples.control;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class EntityManagerFactoryCDI {

    @PersistenceContext(unitName = "tx-cdi")
    EntityManager em;

    @Produces
    public EntityManager newInstance() {
        if(null != em) return em;
        return Persistence.createEntityManagerFactory("tx-cdi").createEntityManager();
    }
}
