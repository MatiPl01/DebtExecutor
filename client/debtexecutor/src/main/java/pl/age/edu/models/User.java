package pl.age.edu.models;

import java.math.BigDecimal;
import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private List<UserGroupDTO> groups;
    private List<UserBalance> balance;
    private BigDecimal totalBalance;

    public User(String id, String firstName, String lastName, List<UserGroupDTO> groups, List<UserBalance> balance, BigDecimal totalBalance) {
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

    public String getId() {
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

        public void setName(String name) {
            this.name = name;
        }
    }
}
