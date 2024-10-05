package com.techelevator.dao;

import com.techelevator.model.ProfessionalCheckIn.ProfessionalCheckIn;

import java.util.List;

public interface ProfessionalCheckInDao {
    public List<ProfessionalCheckIn> getAllProfessionalCheckIns();
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByEmployeeUserId(int id);
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByManagerUserId(int id);
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByEmployeeId(int employeeId);
    public List<ProfessionalCheckIn> getAllProfessionalCheckInsByManagerId(int managerId);
    public ProfessionalCheckIn getProfessionalCheckInByCheckInId(int checkInId);
    public ProfessionalCheckIn createNewProfessionalCheckIn(ProfessionalCheckIn professionalCheckIn,int managerId);
    public ProfessionalCheckIn updateProfessionalCheckIn(ProfessionalCheckIn professionalCheckIn);
    public void deleteProfessionalCheckIn(ProfessionalCheckIn professionalCheckIn);
}
