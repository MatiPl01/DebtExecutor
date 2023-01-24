package pl.edu.agh.debtexecutor.controllers.views.forms.createGroup;

import pl.edu.agh.debtexecutor.models.User;

import java.util.List;

public record GroupFormData(
    String name,
    List<User> users
) {}
