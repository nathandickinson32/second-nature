import axios from "axios";

export default {
    getAllMaintenanceTickets() {
        return axios.get('/maintenance-tickets/all');
    },

    getMaintenanceTicketsByTicketId(ticketId) {
        return axios.get(`maintenance-tickets/${ticketId}`);
    },
    getUserById(id) {
        return axios.get(`/get-user/${id}`)
    }

}