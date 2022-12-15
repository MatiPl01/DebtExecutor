package pl.age.edu.models;

import java.util.List;

public class User {

    public String id;
    public String firstName;
    public String lastName;
    public List<UserGroupDTO> groups;
    public List<UserBalance> balance;

    public User(String id, String firstName, String lastName, List<UserGroupDTO> groups, List<UserBalance> balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = groups;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserGroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroupDTO> groups) {
        this.groups = groups;
    }

    public List<UserBalance> getBalance() {
        return balance;
    }

    public void setBalance(List<UserBalance> balance) {
        this.balance = balance;
    }

    public class UserGroupDTO {
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
