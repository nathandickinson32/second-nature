<template>
 <div class="small-container">
    <span>Maintenance Ticket for <span>{{ modelNumber }}</span></span>
    <span>{{ modelName }} | {{serialNumber }} </span><br/>
    <span>Complete {{ this.MaintenanceTicket.complete }}</span> <br/> 
    <button @click="viewMaintenanceTicket">View Ticket</button>
 </div>
  </template>
  
  <script>
  import router from '../router/index.js';
import EquipmentService from '../services/EquipmentService.js';
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
          modelName: '',
          serialNumber: '',
          modelNumber: '',

      }
    },

  
    
    methods: {
      getModels(MaintenanceTicket){
          EquipmentService.getEquipmentById(this.MaintenanceTicket.equipmentId)
          .then(
            (response) => {
              console.log(response.data)
              this.modelName = response.data.name;
              this.serialNumber= response.data.serialNumber;
              this.modelNumber = response.data.model;
            }
          )
      },
     
      viewMaintenanceTicket() {
        router.push({
          name: 'maintenanceTicketDetails',
          params: {
            ticketId: this.MaintenanceTicket.ticketId
          }
        });
      }
    },
    created() {
      this.getModels(this.MaintenanceTicket);
    }
  }
  
  </script>
  
  
  
  <style scoped>

  </style>