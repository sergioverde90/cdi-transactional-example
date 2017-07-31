package com.sergio.examples.entity;

import javax.persistence.*;

/**
 * Created by Sergio on 26/02/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = CdiEntity.NAMED_QUERY_GET_ALL, query = "select c from CdiEntity c")
})
public class CdiEntity {

    /**
     * Named queries
     */
    public static final String NAMED_QUERY_GET_ALL = "CdiEntity.GET_ALL";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name="name")
    private String name;

    public CdiEntity() { /* default constructor required for entity */}

    public CdiEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
