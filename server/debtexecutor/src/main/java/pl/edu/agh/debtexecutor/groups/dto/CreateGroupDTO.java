package pl.edu.agh.debtexecutor.groups.dto;

import java.util.List;
import java.util.UUID;

public record CreateGroupDTO(String name, List<UUID> users) {}
