<template>
    <div class="container">
       
        <div class="MaintenanceTicket-list" v-if="filteredMaintenanceTickets.length === 0">No Maintenance Tickets have been entered yet.</div>
        <div class="MaintenanceTicket-list" v-else>
            <MaintenanceTicket v-for="MaintenanceTicket in filteredMaintenanceTickets" :key="MaintenanceTicket.ticketId" :MaintenanceTicket="MaintenanceTicket"></MaintenanceTicket>
        </div>
    </div>
</template>

<script>
import MaintenanceTicket from './MaintenanceTicket.vue';
import MaintenanceService from '../services/MaintenanceService';

export default {
    components: {
        MaintenanceTicket
    },
    data() {
        return {
            MaintenanceTickets: [],
            filterType: 'all',
           
        }
    },
    created() {
        this.getMaintenanceTickets();
    },
    computed: {
        filteredMaintenanceTickets() {
            if (this.filterType === 'all') {
                return this.MaintenanceTickets;
            }else if (this.filterType === 'single') {
                return this.MaintenanceTickets.filter(MaintenanceTicket => MaintenanceTicket.equipmentId === this.currentUserId);
            }
            return []; // Default return for unexpected filterType
        },
      
    },
    methods: {
        getMaintenanceTickets() {
            MaintenanceService.getAllMaintenanceTickets()
                .then(
                    (response) => {
                        console.log(response.data);
                        this.MaintenanceTickets = response.data;
                    }
                );
        },
        showAllMaintenanceTickets() {
            this.filterType = 'all';
        },
    }
}
</script>

<style scoped>

</style>