package pl.age.edu.api.group;

import java.util.List;
import java.util.UUID;

public record CreateGroupDTO(String name, List<String> users) {
}

