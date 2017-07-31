package com.sergio.examples.resources;

import com.sergio.examples.control.CdiControl;
import com.sergio.examples.entity.CdiEntity;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Sergio on 26/02/2017.
 */
@Path("cdi")
public class CdiResource {

    @Inject
    CdiControl control;

    public CdiResource() {/* default constructor */}

    @Inject
    public CdiResource(CdiControl cdiControl) {
        this.control = cdiControl;
    }

    @PUT
    @Path("tx")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject tx(CdiEntity entity){
        CdiEntity persisted = control.tx(entity);
        return toJSON(persisted);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAll() {
        List<CdiEntity> all = control.getAll();
        return toJSON(all);
    }

    @GET
    @Path("byId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject byId(@PathParam("id") Long id) {
        CdiEntity found = control.findById(id);
        if(null == found) throw new NoSuchElementException("entity not found with id "+id);
        return toJSON(found);
    }

    private JsonObject toJSON(CdiEntity entity) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", entity.getId());
        builder.add("name", entity.getName());
        return builder.build();
    }

    private JsonArray toJSON(List<CdiEntity> all) {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
        for (CdiEntity cdiEntity : all) {
            arrBuilder.add(objBuilder);
            objBuilder.add("id", cdiEntity.getId());
            objBuilder.add("name", cdiEntity.getName());
        }
        return arrBuilder.build();
    }
}
