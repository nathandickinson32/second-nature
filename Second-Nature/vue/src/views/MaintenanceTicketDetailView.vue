<template>
  <h1>Ticket Details</h1>
  <maintenance-ticket-details></maintenance-ticket-details>
</template>
 
<script>
import MaintenanceTicketDetails from '../components/MAINTENANCE/MaintenanceTicketDetails.vue';
export default {
  components:{
  MaintenanceTicketDetails
}
}
</script>

<style scoped>
h1 {
  text-align: center;
}

.content {
  gap: 10px;
}
</style>