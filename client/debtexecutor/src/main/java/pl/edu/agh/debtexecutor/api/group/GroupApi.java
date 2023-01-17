package pl.edu.agh.debtexecutor.api.group;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.group.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.models.Group;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class GroupApi {
    private final GroupApiService groupApiService;

    public GroupApi(RetrofitClient retrofitClient) {
        groupApiService = retrofitClient.getClient().create(GroupApiService.class);
    }

    public List<Group> getAll() {
        try {
            List<Group> groups = groupApiService.getGroups().execute().body();
            if (groups != null) return groups;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Group> createGroup(CreateGroupDTO dto) {
        try {
            return Optional.ofNullable(groupApiService.createGroup(dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
