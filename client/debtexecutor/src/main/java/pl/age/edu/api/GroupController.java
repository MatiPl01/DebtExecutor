package pl.age.edu.api;

import pl.age.edu.models.Group;
import pl.age.edu.models.User;
import pl.age.edu.utils.CreateUserDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GroupController {

    public static List<Group> getAll() {
        GroupService service = RetrofitClient.getRetrofitClient().create(GroupService.class);
        Optional<Response<List<Group>>> response = Optional.empty();
        try {
            response = Optional.of(service.getGroups().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

}
