package com.sergio.examples.resources;

import com.sergio.examples.control.CdiControl;
import com.sergio.examples.entity.CdiEntity;
import org.junit.Before;
import org.junit.Test;

import javax.enterprise.event.Event;
import javax.enterprise.util.TypeLiteral;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.annotation.Annotation;

/**
 * Created by Sergio on 18/05/2017.
 */
public class CdiResourceTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    CdiResource resource;

    @Before
    public void init() throws ClassNotFoundException {
        System.out.println(getClass().getResource("./"));
        System.out.println(Class.forName("org.hibernate.ejb.HibernatePersistence"));
        this.emf = Persistence.createEntityManagerFactory("tx-cdi");
        this.em = emf.createEntityManager();
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

    @Test
    public void tx() throws Exception {
        resource.tx();
    }

}