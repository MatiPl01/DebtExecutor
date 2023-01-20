package pl.edu.agh.debtexecutor.models;

import java.util.UUID;

public final class Category {
    private final UUID id;
    private final String name;

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
