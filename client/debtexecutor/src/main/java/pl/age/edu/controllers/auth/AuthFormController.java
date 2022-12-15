package pl.age.edu.controllers.auth;

public abstract class AuthFormController {
    protected AuthController parentController;

    public void setParentController(AuthController authController) {
        parentController = authController;
    }
}
