package pl.age.edu.api.group;

import pl.age.edu.api.RetrofitClient;
import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.models.Group;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GroupApi {
    public static List<Group> getAll() {
        GroupApiService service = RetrofitClient.getRetrofitClient().create(
                GroupApiService.class);
        Optional<Response<List<Group>>> response = Optional.empty();
        try {
            response = Optional.of(service.getGroups().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public static void add(CreateGroupDTO dto) {
        GroupApiService service = RetrofitClient.getRetrofitClient().create(
                GroupApiService.class);
        try {
           service.addGroup(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
