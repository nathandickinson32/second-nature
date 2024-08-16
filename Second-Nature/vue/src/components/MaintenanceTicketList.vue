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
            filterType: 'all'
        }
    },
    created() {
        this.getMaintenanceTickets();
    },
    computed: {
        filteredMaintenanceTickets() {
            if (this.filterType === 'all') {
                return this.MaintenanceTickets;
            }
            return []; // Default return for unexpected filterType
        },
        currentUserId() {
            return this.$store.state.user.id;
        }
    },
    methods: {
        getMaintenanceTickets() {
            MaintenanceService.getAllMaintenanceTickets()
                .then(
                    (response) => {
                        console.log(response.data);
                        this.MaintenanceTicket = response.data;
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
.clickable-label {
    color: #2a64bd;
    text-decoration: none;
}

.clickable-label:hover {
    cursor: pointer;
    text-decoration: underline;
}

.clickable-label:visited {
    color: #a1af9f;
}

.filter-section {
    width: 100%;
    text-align: center;
}

.container {
    width: 100%;
    margin: 0;
}

.kudos-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    margin-top: 20px;
    width: 100%;
}
</style>