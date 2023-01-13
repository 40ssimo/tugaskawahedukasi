package id.kawahedukasi.controller;

import id.kawahedukasi.model.Item;
import id.kawahedukasi.model.dto.UploadItemRequest;
import id.kawahedukasi.service.ItemService;
import id.kawahedukasi.service.ReportService;
import id.kawahedukasi.service.UploadService;
import net.sf.jasperreports.engine.JRException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {

    @Inject
    ItemService itemService;
    @Inject
    UploadService uploadService;

    @Inject
    ReportService reportService;

    @GET
    @Path("/report")
    @Produces("application/pdf")
    public Response create() throws JRException {
        return reportService.exportJasper();
    }

    @POST
    public Response create(JsonObject request) {
        return itemService.create(request);
    }

    @GET
    public Response get() {
        List<Item> listItem = Item.listAll();

        return Response.ok().entity(listItem).build();
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@MultipartForm UploadItemRequest request) throws IOException {
        return uploadService.upload(request);
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
