package com.techelevator.model.TrainingResource;

public class ArchiveTrainingResourceDto {
    private int trainingResourceId;
    private String archivedNotes;

    public int getTrainingResourceId() {
        return trainingResourceId;
    }

    public void setTrainingResourceId(int trainingResourceId) {
        this.trainingResourceId = trainingResourceId;
    }

    public String getArchivedNotes() {
        return archivedNotes;
    }

    public void setArchivedNotes(String archivedNotes) {
        this.archivedNotes = archivedNotes;
    }
}
