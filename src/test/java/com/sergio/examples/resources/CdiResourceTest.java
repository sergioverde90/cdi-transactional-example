package com.sergio.examples.resources;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Sergio on 18/05/2017.
 */
public class CdiResourceTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void init() throws ClassNotFoundException {
        System.out.println(getClass().getResource("META-INF/persistence.xml"));
        System.out.println(Class.forName("org.hibernate.ejb.HibernatePersistence"));
        this.emf = Persistence.createEntityManagerFactory("tx-cdi");
        this.em = emf.createEntityManager();
    }

    @Test
    public void tx() throws Exception {
        System.out.println("test!");
        Query query = em.createNativeQuery("select * from CdiEntity");
        List<?> list = query.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

}