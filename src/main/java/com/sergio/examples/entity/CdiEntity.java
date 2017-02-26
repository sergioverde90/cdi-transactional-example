package com.sergio.examples.entity;

import javax.persistence.*;

/**
 * Created by Sergio on 26/02/2017.
 */
@Entity
public class CdiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name="name")
    private String name;

    public CdiEntity() {
        // default constructor required for entity
    }

    public CdiEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"CdiEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"name\":\"" + name + "\""
                + "}}";
    }
}
