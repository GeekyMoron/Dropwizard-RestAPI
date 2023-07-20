package auth;

import core.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class DropAuthenticator implements Authenticator<BasicCredentials, User> {
    public DropAuthenticator(String password) {
        this.password = password;
    }

    private String password;
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if(password.equals(credentials.getPassword()))
            return Optional.of(new User());
        else
        return Optional.empty();
    }
}
