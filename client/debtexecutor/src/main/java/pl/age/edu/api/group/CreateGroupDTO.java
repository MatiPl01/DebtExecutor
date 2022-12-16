package pl.age.edu.api.group;

import java.util.List;

public record CreateGroupDTO(String name, List<String> users) {
}

