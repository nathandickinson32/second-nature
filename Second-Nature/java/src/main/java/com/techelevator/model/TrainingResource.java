package com.techelevator.model;

import java.sql.Timestamp;
import java.util.Date;

public class TrainingResource {
private int trainingResourceId;
private String title;
private String category;
private String content;
private String resourceSource;
private Timestamp enteredOnDate;
private int enteredByUserId;
private Timestamp updatedOnDate;
private int updatedByUserId;
private boolean isArchived;
private String archivedNotes;

    public int getTrainingResourceId() {
        return trainingResourceId;
    }

    public void setTrainingResourceId(int trainingResourceId) {
        this.trainingResourceId = trainingResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getEnteredOnDate() {
        return enteredOnDate;
    }



    public Date getUpdatedOnDate() {
        return updatedOnDate;
    }



    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getArchivedNotes() {
        return archivedNotes;
    }

    public void setArchivedNotes(String archivedNotes) {
        this.archivedNotes = archivedNotes;
    }

    public void setEnteredOnDate(Timestamp enteredOnDate) {
        this.enteredOnDate = enteredOnDate;
    }

    public int getEnteredByUserId() {
        return enteredByUserId;
    }

    public void setEnteredByUserId(int enteredByUserId) {
        this.enteredByUserId = enteredByUserId;
    }

    public void setUpdatedOnDate(Timestamp updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
    }
}
