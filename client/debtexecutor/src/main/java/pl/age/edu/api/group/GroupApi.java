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
    private final static GroupApiService groupService =
            RetrofitClient.getRetrofitClient().create(GroupApiService.class);

    public static List<Group> getAll() {
        Optional<Response<List<Group>>> response = Optional.empty();
        try {
            response = Optional.of(groupService.getGroups().execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public static Optional<Group> createGroup(CreateGroupDTO dto) {
        Optional<Response<Group>> response = Optional.empty();
        try {
            response = Optional.of(groupService.createGroup(dto).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body);
    }
}
