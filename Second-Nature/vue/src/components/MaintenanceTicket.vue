<template>
 <div class="small-container">
    <p>Maintenance Ticket for </p>
 </div>
  </template>
  
  <script>
  import router from '../router/index.js';
import MaintenanceService from '../services/MaintenanceService.js';  
  export default {
    props: {
      MaintenanceTicket: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
  
      }
    },
  
    
    methods: {
     
      viewMaintenanceTicket() {
        router.push({
          name: 'maintenanceTicketDetail',
          params: {
            ticketId: this.MaintenanceTicket.ticketId
          }
        });
      }
    },
    computed: {
    }
  }
  
  </script>
  
  
  
  <style scoped>
  a {
    text-decoration: none;
    color: #3e4a3d;
  }
  
  h3 {
    margin: 0;
  }
  
  h5 {
    margin-bottom: 0;
  }
  </style>