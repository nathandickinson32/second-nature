package com.techelevator.model.TrainingResource;

public class UpdateTrainingResourceDto {
    private int trainingResourceId;
    private String title;
    private String category;
    private String content;
    private String resourceSource;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrainingResourceId() {
        return trainingResourceId;
    }

    public void setTrainingResourceId(int trainingResourceId) {
        this.trainingResourceId = trainingResourceId;
    }

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
