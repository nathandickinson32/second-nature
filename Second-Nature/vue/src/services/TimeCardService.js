import axios from "axios";

export default{
    createNewTimeCard(timeCard){
        return axios.post('/time-card/register', timeCard);
    },
    
}