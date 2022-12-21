package pl.edu.agh.debtexecutor.users.dto;

import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.users.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        List<UserGroupDTO> groups,
        List<UserBalanceDTO> balance,
        BigDecimal totalBalance
) {

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getGroups().stream().map(UserGroupDTO::from).toList(),
                user.getBalance()
                    .entrySet()
                    .stream()
                    .map(entry -> UserBalanceDTO.from(
                            entry.getKey(),
                            entry.getValue()
                    ))
                    .toList(),
                user.getTotalBalance()
        );
    }

    private record UserGroupDTO(UUID id, String name) {
        static UserGroupDTO from(Group group) {
            return new UserGroupDTO(group.getId(), group.getName());
        }
    }

    private record UserBalanceDTO(
            UUID userId,
            String firstName,
            String lastName,
            BigDecimal balance
    ) {
        static UserBalanceDTO from(User user, BigDecimal balance) {
            return new UserBalanceDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    balance
            );
        }
    }
}
