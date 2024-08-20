<template>
  <div class="small-container">
    <span class="label">Ticket Number: </span>{{ MaintenanceTicket.ticketId }} <br />
    <span class="label">Model Number: </span>{{ modelNumber }} <br />
    <span class="label">Model Name: </span>{{ modelName }} <br />
    <span class="label">Is Complete: </span>{{ MaintenanceTicket.complete }} <br />
    <button v-on:click="viewMaintenanceTicket">View Ticket</button>
  </div>
</template>

<script>
import router from '../router/index.js';
import EquipmentService from '../services/EquipmentService.js';

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
    getModels() {
      EquipmentService.getEquipmentById(this.MaintenanceTicket.equipmentId)
        .then(
          (response) => {
            console.log(response.data)
            this.modelName = response.data.name;
            this.serialNumber = response.data.serialNumber;
            this.modelNumber = response.data.model;
          }
        )
    },
    viewMaintenanceTicket() {
      router.push({
        name: 'maintenance-ticket-detail',
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
.label {
  font-weight: normal;
  font-size: 0.8em;
  margin-bottom: 10px;
}
</style>