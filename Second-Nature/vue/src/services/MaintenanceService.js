import axios from "axios";

export default {
    getAllMaintenanceTickets() {
        return axios.get('/maintenance-tickets/all');
    },

    getMaintenanceTicketsByEquipmentId() {
        return axios.get('/get-all-maintenance-tickets-by-equipment-id');
    },
    getUserById(id) {
        return axios.get(`/get-user/${id}`)
    }

}