import axios from "axios";

export default {
    getAllMaintenanceTickets() {
        return axios.get('/get-all-maintenance-tickets');
    },

    getMaintenanceTicketsByEquipmentId() {
        return axios.get('/get-all-maintenance-tickets-by-equipment-id');
    },
    getUserById(id) {
        return axios.get(`/get-user/${id}`)
    }

}