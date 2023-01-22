package pl.edu.agh.debtexecutor.api.group;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.ApiResponseHandler;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.group.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.models.Group;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class GroupApi {
    private final GroupApiService groupApiService;
    private final ApiResponseHandler handler;

    public GroupApi(RetrofitClient retrofitClient, ApiResponseHandler handler) {
        this.handler = handler;
        groupApiService = retrofitClient.getClient().create(GroupApiService.class);
    }

    public List<Group> getAll() {
        Optional<List<Group>> groups = handler.handleResponse(groupApiService.getGroups());
        return groups.orElse(Collections.emptyList());
    }

    public Optional<Group> createGroup(CreateGroupDTO dto) {
        return handler.handleResponse(groupApiService.createGroup(dto));
    }
}
