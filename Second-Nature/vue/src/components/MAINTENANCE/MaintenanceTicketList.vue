<template>
    <div class="content"> 
        <div class="filter-section">
            <label @click="showActiveMaintenanceTickets" class="clickable-label">Active Tickets</label>
            <span class="separator"> | </span>
            <label @click="showAllMaintenanceTickets" class="clickable-label">Show All</label>
            <span class="separator"> | </span>
            <label @click="showCompletedMaintenanceTickets" class="clickable-label">Completed</label>
            <span class="separator"> | </span>
            <label @click="showArchivedTickets" class="clickable-label">Archived</label>
        </div>
        <div class="maintenanceTicket-list" v-if="filteredMaintenanceTickets.length === 0">
            No Maintenance Tickets to show.
        </div>
        <div class="maintenanceTicket-list" v-else>
            <MaintenanceTicket v-for="MaintenanceTicket in filteredMaintenanceTickets" :key="MaintenanceTicket.ticketId"
                :MaintenanceTicket="MaintenanceTicket"></MaintenanceTicket>
        </div>
    </div>
</template>

<script>
import MaintenanceTicket from './MaintenanceTicket.vue';
import MaintenanceService from '../../services/MaintenanceService';

export default {
    components: {
        MaintenanceTicket
    },
    data() {
        return {
            MaintenanceTickets: [],
            filterType: 'active'
        }
    },
    created() {
        this.getMaintenanceTickets();
    },
    computed: {
        filteredMaintenanceTickets() {
            console.log(this.filterType);
            console.log(this.MaintenanceTickets);
            if (this.filterType === 'all') {
                return this.MaintenanceTickets;
            }else if (this.filterType === 'archived') {
                return this.MaintenanceTickets.filter(MaintenanceTicket => MaintenanceTicket.archived === true);
            }else if (this.filterType === 'active') {
                return this.MaintenanceTickets.filter(MaintenanceTicket => MaintenanceTicket.archived === false && MaintenanceTicket.complete === false);
            }else if (this.filterType === 'completed') {
                return this.MaintenanceTickets.filter(MaintenanceTicket => MaintenanceTicket.complete === true && MaintenanceTicket.archived === false);
            }
                return []; // Default return for unexpected filterType
        }
    },
    methods: {
        getMaintenanceTickets() {
            MaintenanceService.getAllMaintenanceTickets()
                .then(
                    (response) => {
                        this.MaintenanceTickets = response.data;
                    }
                );
        },
        showActiveMaintenanceTickets() {
            this.filterType = 'active';
        },
        showAllMaintenanceTickets() {
            this.filterType = 'all';
        },  
        showCompletedMaintenanceTickets() {
            this.filterType = 'completed';
        }, 
        showArchivedTickets(){
            this.filterType ='archived'
        }
    }
}
</script>

<style scoped>
.maintenanceTicket-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    width: 100%;
}
</style>