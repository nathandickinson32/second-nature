package com.techelevator.controller;

import com.techelevator.dao.KudosDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Kudos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class KudosController {
    @Autowired
    private KudosDao kudosDao;

    @Autowired
    private UserDao userDao;

    @PostMapping(path = "/give-kudos")
    public Kudos giveKudos(@RequestBody Kudos kudos, Principal principal){
        int giverUserId = userDao.getUserIdByUsername(principal.getName());
        kudos.setGiverUserId(giverUserId);
        return kudosDao.insertKudos(kudos);
    }

    @PostMapping(path = "/update-kudos")
    public Kudos updateKudos(@RequestBody Kudos kudos){
            return kudosDao.updateKudos(kudos);
    }

    @GetMapping(path = "/get-all-kudos")
    public List<Kudos> getAllKudos(){
        return kudosDao.getAllKudos();
    }

    @GetMapping(path = "/get-my-received-kudos")
    public List<Kudos> getMyReceivedKudos(Principal principal){
        int myUserId = userDao.getUserIdByUsername(principal.getName());
        return kudosDao.getKudosByReceiverUserId(myUserId);
    }

    @GetMapping(path = "/get-my-given-kudos")
    public List<Kudos> getMyGivenKudos(Principal principal){
        int myUserId = userDao.getUserIdByUsername(principal.getName());
        return kudosDao.getKudosByGiverId(myUserId);
    }

    @GetMapping(path = "/get-kudos")
    public Kudos getKudosByKudosId(@RequestBody Kudos kudos){
        return kudosDao.getKudosById(kudos.getKudosId());
    }

    @DeleteMapping(path = "/delete-kudos")
    public void deleteKudos(@RequestBody Kudos kudos){
        kudosDao.deleteKudos(kudos);
    }

    @PostMapping(path = "/archive-kudos")
    public Kudos archiveKudos(@RequestBody Kudos kudos){
        return kudosDao.archiveKudos(kudos);
    }
}
