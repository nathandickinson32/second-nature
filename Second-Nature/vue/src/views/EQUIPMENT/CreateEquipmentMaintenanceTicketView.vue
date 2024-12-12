<template>
    <create-equipment-maintenance-ticket-vue/>
  </template>
  
  <script>
import CreateEquipmentMaintenanceTicketVue from '../../components/EQUIPMENT/CreateEquipmentMaintenanceTicket.vue'

  
  export default {
      components: {
          CreateEquipmentMaintenanceTicketVue,
      }
  }
  </script>
        
  
  <style scoped>
  h1 {
      text-align: center;
  }
  
  .content {
      flex-direction: column;
      align-items: center;
  }
  </style>