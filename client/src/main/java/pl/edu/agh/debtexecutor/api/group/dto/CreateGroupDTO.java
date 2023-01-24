package pl.edu.agh.debtexecutor.api.group.dto;

import java.util.List;
import java.util.UUID;

public record CreateGroupDTO(String name, List<UUID> members) {}
