package com.techelevator.controller;

import com.techelevator.dao.TrainingResourceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.TrainingResource.ArchiveTrainingResourceDto;
import com.techelevator.model.TrainingResource.CreateTrainingResourceDTO;
import com.techelevator.model.TrainingResource.TrainingResource;
import com.techelevator.model.TrainingResource.UpdateTrainingResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path="/training")
@CrossOrigin
@PreAuthorize("isAuthenticated")
public class TrainingResourceController {

    @Autowired
    private TrainingResourceDao trainingResourceDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/all")
    public List<TrainingResource> getTrainingResources(Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed all training resources.");
        return trainingResourceDao.getListOfTrainingResources();
    }
    @GetMapping(path = "/{id}")
    public TrainingResource getTrainingResourceById(@PathVariable int id, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed training resource ID: " + id);
        return trainingResourceDao.getTrainingResourceById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public TrainingResource createTrainingResource(@RequestBody CreateTrainingResourceDTO createTrainingResourceDTO, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is creating a new training resource.");
        return trainingResourceDao.createTrainingResource(createTrainingResourceDTO, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update")
    public TrainingResource updateTrainingResource(@RequestBody UpdateTrainingResourceDto updateTrainingResourceDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating training resource ID: " + updateTrainingResourceDto.getTrainingResourceId());
        return trainingResourceDao.updateTrainingResource(updateTrainingResourceDto, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/archive")
    public TrainingResource archiveTrainingResource(@RequestBody ArchiveTrainingResourceDto archiveTrainingResourceDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is archiving training resource ID: " + archiveTrainingResourceDto.getTrainingResourceId());
        return trainingResourceDao.archiveTrainingResource(archiveTrainingResourceDto, userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTrainingResource(@PathVariable int id, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is deleting training resource ID: " + id);
        trainingResourceDao.deleteTrainingResource(id);
    }
}
