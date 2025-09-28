package io.meritu.meritubackend.domain.pojo;

public enum GoalType {

    TEAM("Team"),
    INDIVIDUAL("Individual");

    private final String value;
    GoalType(String individual) {
        this.value = individual;
    }

    public String getValue() {
        return value;
    }
}
