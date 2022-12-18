package pl.edu.agh.debtexecutor.groups.dto;

import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.users.User;

import java.util.List;
import java.util.UUID;

public record GroupDTO(
    UUID id,
    String name,
    List<GroupMemberDTO> members
) {
    public static GroupDTO from(Group group) {
        return new GroupDTO(
            group.getId(),
            group.getName(),
            group.getMembers().stream().map(GroupMemberDTO::from).toList()
        );
    }

    private record GroupMemberDTO(UUID id, String firstName, String LastName) {
        static GroupMemberDTO from(User user) {
            return new GroupMemberDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
            );
        }
    }
}
