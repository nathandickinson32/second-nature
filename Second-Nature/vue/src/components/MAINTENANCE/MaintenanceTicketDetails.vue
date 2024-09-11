<template>
  <div class="content">

    <div class="large-container">
      <span class="label">Ticket Number: {{ MaintenanceTicket.ticketId }}</span>
      <span class="label">Machine Name: </span> {{ modelName }}
      <span class="label">Model Number: {{ modelNumber }}</span> 
      <span class="label">Serial Number: {{serialNumber }} </span>
      <span class="label">Machine hours at maintenance: {{ this.MaintenanceTicket.hours }}</span>
      
      <div v-for="maintenancePerformed in MaintenanceTicket.maintenancePerformedList" v-bind:key="maintenancePerformed.maintenancePerformedId">
        <hr>
        <span class="label">Maintenance Performed</span> <br>
        <span class="label">Description: </span> {{ maintenancePerformed.description }} <br>
        <span class="label">Performed By: </span>{{ maintenancePerformed.performedBy }} <br>
        <span class="label">Notes: </span> <br>
        {{ maintenancePerformed.notes }}
      </div>

      <span>{{ this.MaintenanceTicket.description }}</span>
      <router-link v-bind:to="{ name: 'maintenance-ticket-modify',params: { ticketId: this.$route.params.ticketId } }">Modify</router-link>

    </div>

  </div>
  
 
  <!-- {{ this.MaintenanceTicket }}
  <br/>
  <br/>
  {{ this.MaintenanceTicket.maintenancePerformedList }}
  {{ modelName }}{{ modelNumber }} {{ serialNumber }} -->
</template>

<script>
import MaintenanceService from "../../services/MaintenanceService";
import EquipmentService from "../../services/EquipmentService";
export default {
  data() {
    return {
      MaintenanceTicket: {},
      maintenancePerformedList: [],
      modelName: "",
      serialNumber: "",
      modelNumber: "",
    };
  },

  mounted() {
    MaintenanceService.getMaintenanceTicketsByTicketId(
      this.$route.params.ticketId
    )
      .then((response) => {
        if (response.status == 200) {
          this.MaintenanceTicket = response.data;
          this.getModels();
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    getModels() {
      EquipmentService.getEquipmentById(
        this.MaintenanceTicket.equipmentId
      ).then((response) => {
        this.modelName = response.data.name;
        this.serialNumber = response.data.serialNumber;
        this.modelNumber = response.data.model;
      });
    },
  },
};
</script>

<style scoped>
.label {
  font-size: 0.8em;
}

.large-container {
  gap: 10px;
}
</style>