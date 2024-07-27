package com.techelevator.controller;

import com.techelevator.dao.ProfessionalCheckInDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Kudos;
import com.techelevator.model.ProfessionalCheckIn;
import com.techelevator.model.TimeOffRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@CrossOrigin
public class ProfessionalCheckInController {
    @Autowired
    private ProfessionalCheckInDao professionalCheckInDao;
    @Autowired
    private UserDao userDao;
    @RequestMapping(path = "/get-all-professional-check-ins", method = RequestMethod.GET)
    public List<ProfessionalCheckIn> getAllProfessionalCheckIns() {
        return professionalCheckInDao.getAllProfessionalCheckIns();
    }


    @RequestMapping(path="/get-all-professional-check-ins-by-employee-user-id", method = RequestMethod.GET)
    public List<ProfessionalCheckIn> getAllProfessionalCheckInByEmployeeUserId(Principal principal) {
        return professionalCheckInDao.getAllProfessionalCheckInsByEmployeeUserId(userDao.getUserIdByUsername(principal.getName()));
    }
    @RequestMapping(path="/get-all-professional-check-ins-by-manager-user-id", method = RequestMethod.GET)
    public List<ProfessionalCheckIn> getAllProfessionalCheckInByManagerUserId(Principal principal) {
        return professionalCheckInDao.getAllProfessionalCheckInsByManagerUserId(userDao.getUserIdByUsername(principal.getName()));
    }

    @RequestMapping(path="/get-all-professional-check-ins-by-employee-id/{employeeId}", method = RequestMethod.GET)
    public List<ProfessionalCheckIn> getProfessionalCheckInsByEmployeeId(@PathVariable int employeeId) {
        return professionalCheckInDao.getAllProfessionalCheckInsByEmployeeId(employeeId);
    }

    @RequestMapping(path="/get-all-professional-check-ins-by-manager-id/{managerId}", method = RequestMethod.GET)
    public List<ProfessionalCheckIn> getProfessionalCheckInsByManagerId(@PathVariable int managerId) {
        return professionalCheckInDao.getAllProfessionalCheckInsByManagerId(managerId);
    }


    @RequestMapping(path="/get-professional-check-in-by-check-in-id/{checkInId}", method = RequestMethod.GET)
    public ProfessionalCheckIn getProfessionalCheckInByCheckInId(@PathVariable int checkInId){
        return professionalCheckInDao.getProfessionalCheckInByCheckInId(checkInId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create-professional-check-in", method = RequestMethod.POST)
    public ProfessionalCheckIn createProfessionalCheckIn(@RequestBody ProfessionalCheckIn professionalCheckIn, Principal principal) {
        int userId = userDao.getUserIdByUsername(principal.getName());
        return professionalCheckInDao.createNewProfessionalCheckIn(professionalCheckIn, userId);
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path="/update-professional-check-in")
    public ProfessionalCheckIn updateProfessionalCheckIn(@RequestBody ProfessionalCheckIn professionalCheckIn){
        return professionalCheckInDao.updateProfessionalCheckIn(professionalCheckIn);
    }

    @DeleteMapping(path = "/delete-professional-check-in")
    public void deleteProfessional(@RequestBody ProfessionalCheckIn professionalCheckIn){
        professionalCheckInDao.deleteProfessionalCheckIn(professionalCheckIn);
    }
}
