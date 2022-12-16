package pl.age.edu.models;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Group {
    UUID id;
    String name;
    List<GroupMemberDTO> members;

    public Group(UUID id, String name, List<GroupMemberDTO> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroupMemberDTO> getMembers() {
        return members;
    }

    public class GroupMemberDTO {
        String firstName;
        String LastName;

        public GroupMemberDTO(String firstName, String lastName) {
            this.firstName = firstName;
            LastName = lastName;
        }
    }

    @Override
    public String toString() {
        if (name != "") {
            return name;
        } else {
            AtomicReference<String> s = new AtomicReference<>("");
            members.forEach(user -> s.set(s + user.firstName + " "));
            return s.get();
        }
    }
}
