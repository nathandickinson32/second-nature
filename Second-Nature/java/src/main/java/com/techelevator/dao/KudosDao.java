package com.techelevator.dao;

import com.techelevator.model.Kudos;

import java.util.List;

public interface KudosDao {
    public Kudos insertKudos(Kudos kudos);
    public Kudos updateKudos(Kudos kudos);
    public List<Kudos> getAllKudos();
    public List<Kudos> getKudosByReceiverUserId(int receiverUserId);
    public List<Kudos> getKudosByGiverId(int giverUserId);
    public Kudos getKudosById(int id);
    public void deleteKudos(Kudos kudos);
    public Kudos archiveKudos(Kudos kudos);
    public Kudos getKudosByKudosId(int kudosId);
}
