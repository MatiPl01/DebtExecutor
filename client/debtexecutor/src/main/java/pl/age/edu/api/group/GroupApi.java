package pl.age.edu.api.group;

import org.springframework.stereotype.Component;
import pl.age.edu.api.RetrofitClient;
import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.models.Group;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class GroupApi {
    private final  GroupApiService groupService;

    public GroupApi() {
        groupService = RetrofitClient.getRetrofitClient().create(GroupApiService.class);
    }

    public  List<Group> getAll() {
        Response<List<Group>> response = null;
        try {
            response = groupService.getGroups().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null) {
            return Collections.emptyList();
        } else {
            return response.body();
        }
    }

    public  Optional<Group> createGroup(CreateGroupDTO dto) {
        Optional<Response<Group>> response = Optional.empty();
        try {
            response = Optional.of(groupService.createGroup(dto).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.map(Response::body);
    }
}
