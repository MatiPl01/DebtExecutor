package pl.edu.agh.debtexecutor.api.group.dto;

import java.util.List;

public record CreateGroupDTO(String name, List<String> users) {}
