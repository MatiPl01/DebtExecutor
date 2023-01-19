package pl.edu.agh.debtexecutor.services;

import javafx.beans.property.SimpleObjectProperty;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.api.user.UserApi;
import pl.edu.agh.debtexecutor.api.user.dto.SignInUserDTO;
import pl.edu.agh.debtexecutor.api.user.dto.SingUpUserDTO;
import pl.edu.agh.debtexecutor.models.User;

import java.util.Optional;

@Service
public class AuthService {
    private final UserApi userApi;

    private final SimpleObjectProperty<User> loggedInUser =
            new SimpleObjectProperty<>();

    private AuthService(UserApi userApi) {
        this.userApi = userApi;
    }

    public Optional<User> singUp(String login, String firstName, String lastName) {
        SingUpUserDTO dto = new SingUpUserDTO(login, firstName, lastName);
        Optional<User> user = userApi.singUp(dto);
        user.ifPresent(loggedInUser::set);
        return user;
    }

    public Optional<User> signIn(String login) {
        SignInUserDTO dto = new SignInUserDTO(login);
        Optional<User> user = userApi.signIn(dto);
        user.ifPresent(loggedInUser::set);
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
