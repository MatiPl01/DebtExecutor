package pl.edu.agh.debtexecutor.user;

import pl.edu.agh.debtexecutor.group.Group;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        List<UserGroupDTO> groups,
        List<UserBalanceDTO> balance
) {

    static UserDTO from(User user) {
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
                    .toList()
        );
    }

    private record UserGroupDTO(String name) {
        static UserGroupDTO from(Group group) {
            return new UserGroupDTO(group.getName());
        }
    }

    private record UserBalanceDTO(
            UUID id,
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
