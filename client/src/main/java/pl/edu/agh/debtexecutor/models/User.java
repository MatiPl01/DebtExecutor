package pl.edu.agh.debtexecutor.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public final class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final List<UserGroupDTO> groups;
    private final List<UserBalance> balance;
    private final BigDecimal totalBalance;

    public User(UUID id,
                String firstName,
                String lastName,
                List<UserGroupDTO> groups,
                List<UserBalance> balance,
                BigDecimal totalBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = groups;
        this.balance = balance;
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<UserGroupDTO> getGroups() {
        return groups;
    }

    public List<UserBalance> getBalance() {
        return balance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public static class UserGroupDTO {
        String name;

        public UserGroupDTO(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
