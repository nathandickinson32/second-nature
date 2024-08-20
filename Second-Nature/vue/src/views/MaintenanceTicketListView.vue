<template>
  <div class="content">
    <h1>Maintenence Tickets</h1>
    <!-- Create a Maintenance Ticket Button -->
    <maintenance-ticket-list/>
  </div>
</template>

<script>
import MaintenanceTicketList from '../components/MaintenanceTicketList.vue';

export default {
components: {
  MaintenanceTicketList
}
}
</script>

<style scoped>
.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}
</style>