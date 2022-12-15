package pl.age.edu.api.user;

import pl.age.edu.api.RetrofitClient;
import pl.age.edu.models.User;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class UserController {

    public static List<User> getAll() {
        UserService service = RetrofitClient.getRetrofitClient().create(UserService.class);
        Optional<Response<List<User>>> response = Optional.empty();
        try {
            response = Optional.of(service.getUsers().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }
}
