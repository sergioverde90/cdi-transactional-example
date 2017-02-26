package com.sergio.examples.resources;

import com.sergio.examples.control.CdiControl;
import com.sergio.examples.entity.CdiEntity;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;

/**
 * Created by Sergio on 26/02/2017.
 */
@Path("cdi")
public class CdiResource {

    @Inject
    CdiControl control;

    @GET
    @Path("tx")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject tx(){
        CdiEntity entity = new CdiEntity(1L, "entity");
        control.tx(entity);
        return Json.createReader(new StringReader(entity.toString())).readObject();
    }
}
