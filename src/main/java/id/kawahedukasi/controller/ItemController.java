package id.kawahedukasi.controller;

import id.kawahedukasi.model.Item;

import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Path("/item")
public class ItemController {

    @POST
    @Transactional
    public Response create(JsonObject request) {

        Item item = new Item();
        item.name = request.getString("name");
        item.count = request.getInt("count");
        item.price = request.getInt("price");
        item.type = request.getString("type");
        item.description = request.getString("description");

        //save
        item.persist();

        return Response.ok().entity(new HashMap<>()).build();

    }

    @GET
    public Response get() {
        List<Item> listItem = Item.listAll();

        return Response.ok().entity(listItem).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, JsonObject request){
        Item item = Item.findById("");
        item.name = request.getString("name");
        item.count = request.getInt("count");
        item.price = request.getInt("price");
        item.type = request.getString("type");
        item.description = request.getString("description");

        //save
        item.persist();

        return Response.ok().entity(new HashMap<>()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id){
        Item.deleteById(id);
        return Response.ok().entity(new HashMap<>()).build();
    }

}
