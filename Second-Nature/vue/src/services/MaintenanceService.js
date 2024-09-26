import axios from "axios";

export default {
    // Create
    // Create Maintenance Ticket
    createMaintenanceTicket(createMaintenanceTicketDto){
        return axios.post('/maintenance-tickets/create', createMaintenanceTicketDto);
    },

    // Read
    getAllMaintenanceTickets() {
        return axios.get('/maintenance-tickets/all');
    },

    getMaintenanceTicketsByTicketId(ticketId) {
        return axios.get(`maintenance-tickets/${ticketId}`);
    },
    getUserById(id) {
        return axios.get(`/get-user/${id}`)
    },

    // Update
    // Update Maintenance Ticket

    updateMaintenanceTicket(updateMaintenanceTicket){
        return axios.post('/maintenance-tickets/update',updateMaintenanceTicket);
    },
    // Complete Maintenance Ticket
    completeMaintenanceTicket(completeMaintenanceTicket){
        return axios.post('/maintenance-tickets/complete',completeMaintenanceTicket);
    },
    // Delete
    // Delete Maintenance Ticket
    deleteMaintenanceTicket(ticketId){
        return axios.delete(`/maintenance-tickets/delete/${ticketId}`);
    },
    // Archive Maintenacne Ticket
    archiveMaintenanceTicket(archiveMaintenanceTicket){
        return axios.post('/maintenance-tickets/archive', archiveMaintenanceTicket);
    }

}