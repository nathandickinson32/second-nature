<template>
  <maintenance-ticket-details></maintenance-ticket-details>
  <FooterVue/>
</template>
 
<script>
import FooterVue from '../../components/Footer.vue';
import MaintenanceTicketDetails from '../../components/MAINTENANCE/MaintenanceTicketDetails.vue';

export default {
  components:{
  MaintenanceTicketDetails,
  FooterVue
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