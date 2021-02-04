package org.acme.mongodb.panache;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    FruitService fruitService;

    @GET
    public List<Fruit> list() {
        return fruitService.list();
    }

    @GET
 	@Path("/{id}")
    public Fruit get(@PathParam("id") String id) {
        return fruitService.get(id);
    }

//    @POST
//    public List<Fruit> add(Fruit fruit) {
//        fruitService.add(fruit);
//        return list();
//    }
}