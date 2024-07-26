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
    },

    updateKudos(kudos) {
        return axios.post('/update-kudos', kudos);
    },

    // Requested modified end point to accept a kudosId instead of kudos
    getKudo(kudos) {
        return axios.post('/get-kudos', kudos);
    },

    deleteKudos(kudos) {
        return axios.post('/delete-kudos', kudos);
    },

    archiveKudos(kudos) {
        return axios.post('/archive-kudos', kudos);
    }
}