<template>
  <h1>Ticket</h1>
  {{ this.MaintenanceTicket }}
  {{ modelName }}{{ modelNumber }} {{ serialNumber }}
</template>

<script>
import MaintenanceService from "../services/MaintenanceService";
import EquipmentService from "../services/EquipmentService";
export default {
  data() {
    return {
      MaintenanceTicket: {},
      modelName: "",
      serialNumber: "",
      modelNumber: "",
    };
  },

  mounted() {
    console.log(this.$route.params.ticketId)
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
        console.log(response.data);
        this.modelName = response.data.name;
        this.serialNumber = response.data.serialNumber;
        this.modelNumber = response.data.model;
      });
    },
  },
};
</script>

<style>
</style>