import axios from "axios";

export default{
    createNewTimeCard(timeCard){
        return axios.post('/time-card/register', timeCard);
    },
    createEmployeeTimeCard(userIdDto){
        return axios.post('/time-card/employee-login', userIdDto);
    },
    getAllTimeCardsByUserId(userId){
        return axios.get(`/time-card/${userId}/time-cards`);
    },
    getTimeCardById(timeCardId){
        return axios.get(`/time-card/${timeCardId}`)
    },
    updateTimeCard(updatedTimeCard){
        return axios.put(`/time-card/update`, updatedTimeCard)
    },
    // getUsernameByTimeCardId(timeCardId){
    //     return axios.get(`/time-card/${timeCardId}/username`)
    // }
    getTimeCardsForCurrentPayPeriod(userId) {
        return axios.get(`/time-card/${userId}/time-sheet`)
    }
}