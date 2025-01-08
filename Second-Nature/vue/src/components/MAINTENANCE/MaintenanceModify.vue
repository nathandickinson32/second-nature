<template>
  <div class="content">
    
    <div class="document-container">
      <h4>Update Maintenance Ticket</h4>
      <form id="ticket-form" @submit.prevent="saveTicket">
        <label for="notes">Notes: </label>
        <textarea name="notes" id="notes" v-model="updateMaintenanceTicket.notes" :readonly="!isManager"></textarea>
        <div class="checkbox-div">
          <input type="checkbox" name="complete" v-model="updateMaintenanceTicket.complete" id="complete" />
          <label for="complete"> Complete</label>
        </div>
        <div class="maintenance-performed" v-for="(performed, index) in updateMaintenanceTicket.createMaintenancePerformedDto" v-bind:key="index">
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
        <input type="submit" value="Save" id="submitTicket">
      </form>
      <div class="button-section">
        <span @click="goBack" class="clickable-label">Back to Details</span>
      </div>
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
        createMaintenancePerformedDto: [],
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
          // console.log(response.data);
          alert('Maintenance Ticket Modifications updated!');

          this.$router.push({ name: 'maintenance-ticket-detail', params: { ticketId: this.updateMaintenanceTicket.ticketId } });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addMaintenancePerformed(){
            this.updateMaintenanceTicket.createMaintenancePerformedDto.push(
                {
                    description : '',
                    performedBy : '',
                    notes : 'Additional notes'
                }
            );
        },
        subtractMaintenancePerformed(){
            this.updateMaintenanceTicket.createMaintenancePerformedDto.pop();
        },
  },
};
</script>

<style scoped>
button {
    width: 100%;
    height: 4em;
}

form {
    align-items: baseline;
    gap: 10px;
}

input {
    margin-bottom: 10px;
    width: 100%;
    box-sizing: border-box;
    height: 4em;
}

label {
    font-size: 0.8em;
}

textarea {
    resize: none;
    box-sizing: border-box;
    width: 100%;
    height: 6em;
    margin-bottom: 10px;
}

#submitTicket {
    height: 4em;
}

.button-section {
  text-align: center;
  margin-top: 20px;
  margin-bottom: 20px;
}

.checkbox-div {
    display: flex;
    justify-content: baseline;
    align-items: center;
    margin-bottom: 10px;
}

.checkbox-div input {
    width: 30px;
    height: 30px;
    margin-right: 10px;
}

.content {
  gap: 10px;
}

.label {
    font-size: 0.8em;
}
</style>
