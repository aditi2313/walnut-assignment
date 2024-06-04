package org.acme;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

@Path("/")
public class GreetingResource {

    @Inject
    GreetingService service;

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(String name) {
        return service.greeting(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/task1")
    public String task1(String json) {
        return json;
    }

    @POST
    @Path("/savePerson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePerson(String json) {
        Jsonb jsonb = JsonbBuilder.create();
        Person person = jsonb.fromJson(json, Person.class);
        Person savedPerson = personService.savePerson(person.getName());
        return Response.ok(savedPerson).build();
    }

    @GET
    @Path("/getPeople")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNames() {
        List<String> names = personService.getAllNames();
        return Response.ok(names).build();
    }
}