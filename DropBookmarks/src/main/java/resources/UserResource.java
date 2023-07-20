package resources;

import core.User;
import dao.UserDao;
import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/users")
public class UserResource {
    public static final Logger LOG = LoggerFactory.getLogger(UserResource.class);
    public final UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<model.User> getUsers(@Auth User user, @QueryParam("userId") int UserId) {
        LOG.info("Fetching User Records");
      return UserDao.getUsers(UserId);
    }
}
