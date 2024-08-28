package com.techelevator.controller;

import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.MaintenanceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/equipment")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class EquipmentController {
    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private MaintenanceDao maintenanceDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/get-all")
    public List<Equipment> getEquipment(Principal principal) {
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed all equipment.");
        return equipmentDao.getListOfAllEquipment();
    }

    @GetMapping(path = "/{id}")
    public Equipment getEquipmentById(@PathVariable int id, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed equipment ID: " + id);
        return equipmentDao.getEquipmentById(id);
    }

    @GetMapping(path = "/{equipmentId}/maintenance-performed")
    public List<MaintenancePerformed> getMaintenancePerformedByEquipmentId(@PathVariable int equipmentId, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed a list of maintenance performed for equipment ID: " + equipmentId);
        return maintenanceDao.getMaintenancePerformedByEquipmentId(equipmentId);
    }

    @GetMapping(path = "/identity-list")
    public List<EquipmentIdentityDto> getEquipmentIdentityList(Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed all equipment identities.");
        return equipmentDao.getEquipmentIdentityList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public Equipment createEquipment(@RequestBody CreateEquipmentDto createEquipmentDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is creating a new equipment.");
        return equipmentDao.createEquipment(createEquipmentDto, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update")
    public Equipment updateEquipment(@RequestBody UpdateEquipmentDto updateEquipmentDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating equipment ID: " + updateEquipmentDto.getEquipmentId());
        return equipmentDao.updateEquipment(updateEquipmentDto, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update-activity")
    public Equipment updateEquipmentActivity(@RequestBody UpdateEquipmentActivityDto updateEquipmentActivityDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating active status for equipment ID: " + updateEquipmentActivityDto.getEquipmentId());
        return equipmentDao.updateEquipmentActivity(updateEquipmentActivityDto, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/archive")
    public Equipment archiveEquipment(@RequestBody ArchiveEquipmentDto archiveEquipmentDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is archiving equipment ID: " + archiveEquipmentDto.getEquipmentId());
        return equipmentDao.archiveEquipment(archiveEquipmentDto, userId);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteEquipment(@PathVariable int id, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is deleting equipment ID: " + id);
        equipmentDao.deleteEquipment(id);
    }
}
