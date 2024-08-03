import axios from "axios";

export default {
    getAllProfessionalCheckIns(){
        return axios.get('/get-all-professional-check-ins');
    }, 
    getAllProfessionalCheckInByEmployeeUserId(){
        return axios.get('/get-all-professional-check-ins-by-employee-user-id');
    },
    getAllProfessionalCheckInByManagerUserId(){
        return axios.get('/get-all-professional-check-ins-by-manager-user-id');
    },
    getProfessionalCheckInByCheckInId (id) {
        return axios.get(`/get-professional-check-in-by-check-in-id/${id}`)
    },
    addNewProfessionalCheckInByCheckIn(ProfessionalCheckIn){
        return axios.post('/create-professional-check-in', ProfessionalCheckIn);
    },
    updateProfessionalCheckIn(ProfessionalCheckIn){
        return axios.post('/update-professional-check-in', ProfessionalCheckIn);
    },
    deleteProfessionalCheckIn(ProfessionalCheckIn){
        return axios.delete('/delete-professional-check-in', ProfessionalCheckIn);
    }, 
    getUserById(id) {
        return axios.get(`/get-user/${id}`)
    },
    getUserInformation(id){
        return axios.get('/get-all-user-information');
    }
}