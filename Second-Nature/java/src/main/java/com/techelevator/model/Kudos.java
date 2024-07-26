package com.techelevator.model;

import java.util.Date;

public class Kudos {
    private int kudosId;
    private int giverUserId;
    private int receiverUserId;
    private Date date;
    private String title;
    private String notes;
    private boolean archive;

    public int getKudosId() {
        return kudosId;
    }

    public void setKudosId(int kudosId) {
        this.kudosId = kudosId;
    }

    public int getGiverUserId() {
        return giverUserId;
    }

    public void setGiverUserId(int giverUserId) {
        this.giverUserId = giverUserId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
}
