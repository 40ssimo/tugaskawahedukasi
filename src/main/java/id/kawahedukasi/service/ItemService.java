package id.kawahedukasi.service;

import id.kawahedukasi.model.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@ApplicationScoped
public class ItemService {
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

}
