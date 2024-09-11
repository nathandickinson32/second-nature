<template>
    <div class="content">   
      <div class="button-section">
          <span @click="goBack" class="clickable-label">Back to Details</span>
          <span class="separator"> | </span>
          <span @click="saveTicket" class="clickable-label">Save Changes</span>
      </div>
      <div class="large-container">
          <form id="ticket-form" @submit.prevent="onSubmit">
              
              <div class="form-input-group">
                  <label for="title">Notes: </label>
                  <input type="text" v-model="updateMaintenanceTicket.notes" id="notes" required />
              </div>
           
           
             
          </form>
      </div>
   
    </div>
  </template>
  
  <script>
import MaintenanceService from '../../services/MaintenanceService'
  
  export default {
   
      data() {
          return {
              MaintenanceTicket: {},
              updateMaintenanceTicket: {}
          }
      },
      mounted(){
          MaintenanceService.getMaintenanceTicketsByTicketId(this.$route.params.ticketId).then((response) => {
              if (response.status == 200) {
                  this.maintenanceTicket = response.data;

                  this.updateMaintenanceTicket.ticketId = this.maintenanceTicket.ticketId;
                  this.updateMaintenanceTicket.equipmentId = this.maintenanceTicket.equipmentId;    
                  this.updateMaintenanceTicket.hours = this.maintenanceTicket.hours;  
                  this.updateMaintenanceTicket.enteredByUserId = this.maintenanceTicket.enteredByUserId;
                  this.updateMaintenanceTicket.date = this.maintenanceTicket.date;
                  this.updateMaintenanceTicket.notes = this.maintenanceTicket.notes;
                  this.updateMaintenanceTicket.isComplete = this.maintenanceTicket.isComplete;
                  this.updateMaintenanceTicket.updatedByUserId = this.maintenanceTicket.updatedByUserId;  
                  this.updateMaintenanceTicket.updatedOnDate = this.maintenanceTicket.updatedOnDate;
                  this.updateMaintenanceTicket.isArchived = this.maintenanceTicket.isArchived;
                  this.updateMaintenanceTicket.archivedNotes = this.maintenanceTicket.archivedNotes;
                  this.updateMaintenanceTicket.maintenancePerformedList = this.maintenanceTicket.maintenancePerformedList;

                }
          }).catch((error) => {
              console.log(error);
          })
      },
      methods: {
          goBack() {
              this.$router.push({ name: 'maintenance-ticket-detail', params: { ticketId: this.updateMaintenanceTicket.ticketId } });
          },
          saveTicket() {
              MaintenanceService.updateMaintenanceTicket(this.updateMaintenanceTicket)
              .then(response => {
                  console.log(response.data);
                  alert('Maintenance Ticket Modifications updated!');
               
                  this.$router.push({ name: 'maintenance-ticket-detail', params: { ticketId: this.updateMaintenanceTicket.ticketId } });
  
              })
              .catch(error => {
                  console.log(error);
              });
          },
          
      }
  }
  </script>
  
  <style scoped>
  .content {
      flex-direction: column;
  }
  
  .button-section {
      text-align: center;
      margin-top: 20px;
      margin-bottom: 20px;
  }
  
  </style>