import axios from "axios";

export default {
    getAllTimeOffRequests(){
        return axios.get('/get-all-time-off-requests');
    }, 
    getAllTimeOffRequestsByUserName(){
        return axios.get('/get-all-time-off-requests-by-username');
    },
    getAllTimeOffRequestsByUserId(){
        return axios.get('/get-all-time-off-requests-by-user-id');
    },
    getTimeOffRequestByRequestId (id) {
        return axios.get(`/get-time-off-request-by-request-id/${id}`)
    },
    addNewTimeOffRequest(TimeOffRequest){
        return axios.post('/add-new-time-off-request', TimeOffRequest);
    },
    updateTimeOffRequest(TimeOffRequest){
        return axios.post('/update-time-off-request', TimeOffRequest);
    },
    deleteTimeOffRequest(TimeOffRequest){
        return axios.delete('/delete-time-off-request', TimeOffRequest);
    }, 
    getUserById(id) {
        return axios.get(`/get-user/${id}`)
    }
}