<template>
  <div class="content">

    <div class="large-container">
      <span class="label">Ticket Number: {{ MaintenanceTicket.ticketId }}</span>
      <span class="label">Machine Name:  {{ modelName }} </span>
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
      <div> 
        <router-link v-if="isManager" v-bind:to="{ name: 'maintenance-ticket-modify',params: { ticketId: this.$route.params.ticketId } }">Modify</router-link>
      </div>
      <div>
        <button v-if="!MaintenanceTicket.archived && isManager" id="archive-ticket" @click="toggleAttemptArchive" class="button">Archive?</button>
        <button v-if="!MaintenanceTicket.complete" id="archive-ticket" @click="completeTicket" class="button">Mark as Complete</button>
      </div>
      <div v-if="archiveAttempted && isManager">
        <span class="label">Note on why archiving this ticket: </span> 
        <input type="archiveNotes" id="archiveNotes" v-model="archiveNotes" placeholder="Enter notes here" autofocus /> <br>
        <button id="archive-ticket" @click="archiveTicket" class="button" :disabled="!archiveNotes" >Archive</button>
      </div>
    </div>
  </div>
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
      archiveNotes: "",
      archiveAttempted: false
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
    createArchiveTicket() {
      return {
        ticketId: this.MaintenanceTicket.ticketId,
        archived: true,
        archivedNotes: this.archiveNotes,
      };
    },
    createCompleteTicket() {
      return {
        ticketId: this.MaintenanceTicket.ticketId,
        complete: true,
      };
    },
    archiveTicket() {
      const archiveTicket = this.createArchiveTicket();
      MaintenanceService.archiveMaintenanceTicket(archiveTicket)
      .then(() => {
        window.alert('Maintenance Ticket archived!');
        this.$router.push({ name: 'maintenance-ticket-List' });
      }).catch(() => {
        console.log("Error archiving the ticket.");
      });
    },
    completeTicket() {
      const completeTicket = this.createCompleteTicket();
      MaintenanceService.completeMaintenanceTicket(completeTicket)
      .then(() => {
        window.alert('Ticket marked as complete!');
        this.$router.push({ name: 'maintenance-ticket-List' });
      }).catch(() => {
        console.log("Error completing ticket.");
      });
    },
    toggleAttemptArchive() {
      this.archiveAttempted = !this.archiveAttempted;
    }
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  }
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