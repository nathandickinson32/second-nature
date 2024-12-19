<template>
    <div class="content">
      <h1>Maintenence Tickets</h1>
      <router-link v-bind:to="{ name: 'create-maintenance-ticket',  params: { equipmentId: this.$route.params.equipmentId }}" id="create-ticket-btn">Create a Ticket</router-link>
      <equipment-maintenance-ticket-list-vue/>
    </div>
  </template>
  
  <script>
import EquipmentMaintenanceTicketListVue from '../../components/MAINTENANCE/EquipmentMaintenanceTicketList.vue';
  export default {
  components: {
   EquipmentMaintenanceTicketListVue,
  }
  }
  </script>
  
  <style scoped>
  #create-ticket-btn {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #2a64bd ;
    border-radius: 10px;
    background-color: white;
    width: 90%;
    max-width: 400px;
    height: 50px;
    text-align: center;
    color: #2a64bd;
  }
  
  .content {
    flex-direction: column;
    align-items: center;
    gap: 10px;
  }
  </style>