package id.kawahedukasi.service;

import id.kawahedukasi.model.Item;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.scheduler.Scheduled;
import org.jboss.resteasy.annotations.Query;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;

public class SchedulerService {

    @Transactional
    @Scheduled(every="1h")
    public Response delete(Integer count, Integer id ){
        if (count == 0) {
            Item.deleteById(id);
            return Response.ok().entity(new HashMap<>()).build();
        } else {
            return null;
        }

    }
}
