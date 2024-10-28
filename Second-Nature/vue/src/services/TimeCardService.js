import axios from "axios";

export default{
    createNewTimeCard(timeCard){
        return axios.post('/time-card/register', timeCard);
    },
    getAllTimeCardsByUserId(userId){
        return axios.get(`/time-card/${userId}/time-cards`);
    },
    
}