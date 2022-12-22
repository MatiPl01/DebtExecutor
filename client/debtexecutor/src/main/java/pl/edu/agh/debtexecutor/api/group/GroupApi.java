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
    private final  GroupApiService groupService;

    public GroupApi(RetrofitClient retrofitClient) {
        groupService = retrofitClient.getClient().create(GroupApiService.class);
    }

    public List<Group> getAll() {
        try {
            List<Group> groups = groupService.getGroups().execute().body();
            if (groups != null) return groups;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<Group> createGroup(CreateGroupDTO dto) {
        try {
            return Optional.ofNullable(groupService.createGroup(dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
