package com.techelevator.dao;

import com.techelevator.model.TrainingResource.ArchiveTrainingResourceDto;
import com.techelevator.model.TrainingResource.CreateTrainingResourceDTO;
import com.techelevator.model.TrainingResource.TrainingResource;
import com.techelevator.model.TrainingResource.UpdateTrainingResourceDto;

import java.util.List;

public interface TrainingResourceDao {
    // Create
    public TrainingResource createTrainingResource(CreateTrainingResourceDTO createTrainingResourceDTO, int userId);

    // Read
    public List<TrainingResource> getListOfTrainingResources();
    public TrainingResource getTrainingResourceById(int trainingResourceId);

    // Update
    public TrainingResource updateTrainingResource(UpdateTrainingResourceDto updateTrainingResourceDto, int userId);

    // Archive
    public TrainingResource archiveTrainingResource(ArchiveTrainingResourceDto archiveTrainingResourceDto, int userId);

    // Delete
    public void deleteTrainingResource(int trainingResourceId);

}
