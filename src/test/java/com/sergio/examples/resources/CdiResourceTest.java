package com.sergio.examples.resources;

import com.sergio.examples.control.CdiControl;
import com.sergio.examples.entity.CdiEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.enterprise.event.Event;
import javax.enterprise.util.TypeLiteral;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.annotation.Annotation;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Sergio on 18/05/2017.
 */
public class CdiResourceTest {

    static EntityManagerFactory emf;
    static EntityManager em;

    CdiResource resource;

    @BeforeClass
    public static void postConstruct() {
        emf = Persistence.createEntityManagerFactory("tx-cdi");
        em  = emf.createEntityManager();
    }

    @Before
    public void init() throws ClassNotFoundException {
        startTransaction();
        resource = new CdiResource(new CdiControl(new Event<CdiEntity>() {
            @Override
            public void fire(CdiEntity event) {
                System.out.println("event = " + event);
            }

            @Override
            public Event<CdiEntity> select(Annotation... qualifiers) {
                return null;
            }

            @Override
            public <U extends CdiEntity> Event<U> select(Class<U> subtype, Annotation... qualifiers) {
                return null;
            }

            @Override
            public <U extends CdiEntity> Event<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
                return null;
            }
        }, em));
    }

    @After
    public void after() {
        endTransaction();
    }

    /**
     * Helper method to start BMT transaction
     */
    private void startTransaction() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
    }

    /**
     * Helper method to end BMT transaction
     */
    private void endTransaction() {
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()) tx.commit();
    }

    @Test
    public void tx() throws Exception {
        resource.tx(new CdiEntity(1L, "name1"));
        resource.tx(new CdiEntity(2L, "name2"));
        JsonArray all = resource.getAll();
        assertEquals(all.size(), 2);
    }

    @Test
    public void getAll() {
        resource.getAll();
    }

    @Test
    public void findById() {
        JsonObject found = resource.byId(1L);
        assertNotNull(found);
    }

    @Test(expected = NoSuchElementException.class)
    public void findByIdNotFound() {
        resource.byId(-1L);
    }

}