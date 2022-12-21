package pl.age.edu.state;

import javafx.beans.property.SimpleObjectProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.models.User;

import java.util.Optional;

@Component
public class AuthState {
    private final SimpleObjectProperty<User> loggedInUser =
            new SimpleObjectProperty<>();

    @Autowired
    private UserApi userApi;

    public Optional<User> singUp(String login, String firstName, String lastName) {
        Optional<User> user = userApi.singUp(login, firstName, lastName);
        if (user.isPresent()) {
            loggedInUser.set(user.get());
        }
        return user;
    }

    public Optional<User> signIn(String login) {
        Optional<User> user = userApi.signIn(login);
        if (user.isPresent()) {
            loggedInUser.set(user.get());
        }
        return user;
    }



    public Optional<User> getLoggedInUser() {
        return Optional.ofNullable(loggedInUser.get());
    }

    public SimpleObjectProperty<User> loggedInUserProperty() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        loggedInUser.set(user);
    }
}
