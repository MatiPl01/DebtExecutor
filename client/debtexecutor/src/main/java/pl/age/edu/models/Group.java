package pl.age.edu.models;

import java.util.List;
import java.util.UUID;

public class Group {
    private final UUID id;
    // TODO - make name field optional
    private final String name;
    private final String placeholderName;
    private final List<GroupMemberDTO> members;

    public Group(UUID id, String name, String placeholderName, List<GroupMemberDTO> members) {
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

    public String getName() {
        return name;
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
