package org.jboss.tyr.model;

public class Label {
    private String name;
    private String description;
    private final String color = "ffffff";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public Label(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
