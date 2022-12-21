package pl.age.edu.api.user;

import org.springframework.stereotype.Component;
import pl.age.edu.api.RetrofitClient;
import pl.age.edu.api.user.dto.SignInUserDTO;
import pl.age.edu.api.user.dto.SingUpUserDTO;
import pl.age.edu.models.User;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserApi {
    private final  UserApiService userService;

    public UserApi() {
        userService = RetrofitClient.getRetrofitClient().create(UserApiService.class);
    }

    public  List<User> getAll() {
        Optional<Response<List<User>>> response = Optional.empty();
        try {
            response = Optional.of(userService.getUsers().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public Optional<User> signIn(String login) {
        Optional<Response<User>> response;
        try {
            SignInUserDTO dto = new SignInUserDTO(login);
           response = Optional.of(userService.singInUser(dto).execute());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.map(Response::body);
    }

    public Optional<User> singUp(
            String login,
            String firstName,
            String lastName
    ) {
        Optional<Response<User>> response = Optional.empty();
        try {
            SingUpUserDTO dto = new SingUpUserDTO(login, firstName, lastName);
            response = Optional.of(userService.signUpUser(dto).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body);
    }
}
