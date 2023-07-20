package resources;

import core.User;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGreeting() {
        return "hello-world";
    }

    @GET
    @Path("/secure")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSecureGreeting(@Auth User user) {
        return "hello-world securely ...";
    }

}
