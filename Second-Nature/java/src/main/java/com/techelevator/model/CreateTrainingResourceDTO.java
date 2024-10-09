package com.techelevator.model;

public class CreateTrainingResourceDTO {
    private String category;
    private String content;
    private String resourceSource;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourceSource() {
        return resourceSource;
    }

    public void setResourceSource(String resourceSource) {
        this.resourceSource = resourceSource;
    }
}
