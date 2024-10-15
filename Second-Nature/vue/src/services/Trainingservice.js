import axios from "axios";

export default {
    createTraining(createTraining) {
        return axios.put('/training/create', createTraining);
    },
    getAllTrainings() {
        return axios.get('/training/all');
    },
    getTraining(trainingId) {
        return axios.get(`/training/${trainingId}`);
    },
    updateTraining(updateTraining) {
        return axios.post('/training/update', updateTraining);
    },
    archiveTraining(archiveTraining) {
        return axios.post('/training/archive', archiveTraining);
    },
    deleteTraining(trainingId) {
        return axios.delete(`/training/delete/${trainingId}`);
    }
}

// TrainingResource (Model)
// {
// 	"training_resource_id" : Integery / Number,
// 	"title" : String
// 	"category" : String (options include: "general", "policies", "winter", "spring", "summer", etc...)
// 	"content" : String,
// 	"resource_source" : String (upload location),
// 	"entered_on_date" : Time Stamp,
// 	"entered_by_user_id" : Intiger,
// 	"updated_on_date" : Time Stamp,
// 	"updated_by_user_id" : Intiger,
// 	"archived" : Boolean (Defaults to false),
// 	"archived_notes" : String (Required once the archived option is selected)
// }

// CreateTrainingResourceDTO
// {
// 	"title" : String
// 	"category" : String (options include: "general", "policies", "winter", "spring", "summer", etc...)
// 	"content" : String,
// 	"resource_source" : String (upload location),
// }
// // Entered on date, and user id is handled on the backend Create controller.

// Update Training Resource DTO
// {
// 	"training_resource_id" : Intiger,
// 	"title" : String
// 	"category" : String (options include: "general", "policies", "winter", "spring", "summer", etc...),
// 	"content" : String,
// 	"resource_source" : String (upload location),
// }
// // Updated on date, and user id is handled on the backend Update controller.

// Archive Training Resource DTO
// {
// 	"training_resource_id" : Integery / Number,
// 	"archived_notes" : String (Required once the archived option is selected)
// }
// // updatedOnDate and updatedByUserId are handled on the backend by the principal.
// // isArchived is updated on the backend and set to TRUE.