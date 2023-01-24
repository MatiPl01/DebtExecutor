package pl.edu.agh.debtexecutor.models;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class Group {
    private final UUID id;
    private final String name;
    private final String placeholderName;
    private final List<GroupMemberDTO> members;

    public Group(UUID id,
                 String name,
                 String placeholderName,
                 List<GroupMemberDTO> members) {
        this.id = id;
        this.name = name;
        this.placeholderName = placeholderName;
        this.members = members;
    }

    @Override
    public String toString() {
        return (name == null || name.length() == 0) ? placeholderName : name;
    }

    public UUID getId() {
        return id;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public String getPlaceholderName() {
        return placeholderName;
    }

    public List<GroupMemberDTO> getMembers() {
        return members;
    }

    public static class GroupMemberDTO {
        String firstName;
        String lastName;

        public GroupMemberDTO(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
