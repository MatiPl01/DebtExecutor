package pl.age.edu.controllers.core;

public enum ThemeType {
    LIGHT("light"),
    DARK("dark");

    private final String themeName;

    ThemeType(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return themeName;
    }
}
