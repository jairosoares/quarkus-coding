package org.acme.controller;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.acme.domain.Fruit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @GET
    public Uni<List<Fruit>> findAll() {
        return Fruit.listAll(Sort.by("name"));
    }

    @POST
    public Uni<Response> save(Fruit dto) {
        return Panache.<Fruit>withTransaction(dto::persist)
                .onItem()
                .transform(inserted -> Response.created(URI.create("/fruits/" + inserted.id)).build());
    }

}