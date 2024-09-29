<template>
  <div class="content">
    <div class="button-section">
      <span @click="goBack" class="clickable-label">Back to Details</span>
      <span class="separator"> | </span>
      <span @click="saveTicket" class="clickable-label">Save Changes</span>
    </div>
    <div class="large-container">
      <form id="ticket-form" @submit.prevent="saveTicket">
        <div class="form-input-group">
          <label for="notes">Notes: </label>
          <input type="text" v-model="updateMaintenanceTicket.notes" id="notes" required :readonly="!isManager" />
        </div>
        <div class="form-input-group">
          <label for="complete">Complete: </label>
          <input type="checkbox" v-model="updateMaintenanceTicket.complete" id="isComplete" required />
        </div>
        <div class="form-input-group" v-for="(performed, index) in maintenanceTicket.maintenancePerformedList" v-bind:key="index">
          <h4>Maintenance Performed:</h4>
          <input
            type="text"
            name="maintenance-performed-description"
            id=""
            placeholder="What is being done?"
            v-model="performed.description"
            :readonly="!isManager"
          />
          <input
            type="text"
            name="maintenance-performed-by"
            id=""
            placeholder="Who is doing the work?"
            v-model="performed.performedBy"
            :readonly="!isManager"
          />
          <textarea name="maintenance-performed-notes" id="maintenance-performed-notes" v-model="performed.notes" :readonly="!isManager"></textarea>
        </div>
        <button v-on:click.prevent="addMaintenancePerformed">Add another maintenance performed</button>
        <button v-on:click.prevent="subtractMaintenancePerformed">Remove last maintenance performed</button>
      </form>
    </div>
  </div>
</template>

<script>
import MaintenanceService from '../../services/MaintenanceService';

export default {
  data() {
    return {
      maintenanceTicket: {},
      updateMaintenanceTicket: {
        ticketId: '',
        notes: '',
        complete: false,
        createMaintenancePerformedDto: [
          {
            description: '',
            performedBy: '',
            notes: '',
          },
        ],
      },
    };
  },
  mounted() {
    MaintenanceService.getMaintenanceTicketsByTicketId(this.$route.params.ticketId)
      .then((response) => {
        if (response.status == 200) {
          this.maintenanceTicket = response.data;

          this.updateMaintenanceTicket.ticketId = this.maintenanceTicket.ticketId;
          this.updateMaintenanceTicket.equipmentId = this.maintenanceTicket.equipmentId;
          this.updateMaintenanceTicket.hours = this.maintenanceTicket.hours;
          this.updateMaintenanceTicket.enteredByUserId = this.maintenanceTicket.enteredByUserId;
          this.updateMaintenanceTicket.date = this.maintenanceTicket.date;
          this.updateMaintenanceTicket.notes = this.maintenanceTicket.notes;
          this.updateMaintenanceTicket.complete = this.maintenanceTicket.complete;
          this.updateMaintenanceTicket.updatedByUserId = this.maintenanceTicket.updatedByUserId;
          this.updateMaintenanceTicket.updatedOnDate = this.maintenanceTicket.updatedOnDate;
          this.updateMaintenanceTicket.archived = this.maintenanceTicket.isArchived;
          this.updateMaintenanceTicket.archivedNotes = this.maintenanceTicket.archivedNotes;
          this.updateMaintenanceTicket.maintenancePerformedList = this.maintenanceTicket.maintenancePerformedList;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
  },
  methods: {
    goBack() {
      this.$router.push({ name: 'maintenance-ticket-detail', params: { ticketId: this.updateMaintenanceTicket.ticketId } });
    },
    saveTicket() {
      MaintenanceService.updateMaintenanceTicket(this.updateMaintenanceTicket)
        .then((response) => {
          console.log(response.data);
          alert('Maintenance Ticket Modifications updated!');

          this.$router.push({ name: 'maintenance-ticket-detail', params: { ticketId: this.updateMaintenanceTicket.ticketId } });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
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
