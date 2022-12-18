package pl.age.edu.api.group.dto;

import java.util.List;

public record CreateGroupDTO(String name, List<String> users) {
}

