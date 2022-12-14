package pl.edu.agh.debtexecutor.group;

import pl.edu.agh.debtexecutor.user.User;

import java.util.List;
import java.util.UUID;

record GroupDTO(
        UUID id,
        String name,
        List<GroupMemberDTO> members
) {
    static GroupDTO from(Group group) {
        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.getMembers().stream().map(GroupMemberDTO::from).toList()
        );
    }

    private record GroupMemberDTO(String firstName, String LastName) {
        static GroupMemberDTO from(User user) {
            return new GroupMemberDTO(user.getFirstName(), user.getLastName());
        }
    }
}
