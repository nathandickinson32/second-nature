import axios from "axios";

export default {
    getAllKudos() {
        return axios.get('/get-all-kudos');
    },

    getMyReceivedKudos() {
        return axios.get('/get-my-received-kudos');
    },

    getMyGivenKudos() {
        return axios.get('/get-my-given-kudos');
    },

    giveKudos(kudos) {
        return axios.post('/give-kudos', kudos);
    }
}