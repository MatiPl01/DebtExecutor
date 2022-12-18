package pl.age.edu.api.group;

import pl.age.edu.api.RetrofitClient;
import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.models.Group;
import retrofit2.Response;

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
        System.out.println(response.map(Response::body).orElse(Collections.emptyList()).stream().map(g -> g.getName()));
        return response.map(Response::body).orElse(Collections.emptyList());
    }

    public static void add(CreateGroupDTO dto) {
        GroupService service = RetrofitClient.getRetrofitClient().create(GroupService.class);
        try {
           service.addGroup(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
