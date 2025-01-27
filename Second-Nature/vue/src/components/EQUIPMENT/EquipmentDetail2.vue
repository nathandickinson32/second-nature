<template>
  <div class="content">
    <div class="button-section">
      <router-link v-if="isManager" v-bind:to="{ name: 'equipment-modify' }"
        >Modify</router-link
      >
      <span class="separator" v-if="isManager"> | </span>
      <router-link v-bind:to="{ name: 'equipmentList' }"
        >Back to Equipment List</router-link
      >
    </div>
    <div class="document-container">
      <form
        v-if="isManager"
        id="equipment-activity-form"
        @submit.prevent="onSubmit"
      >
        <span>
          <input
            v-model="statusEquipment.active"
            type="radio"
            id="activeBtn"
            :value="true"
            :checked="equipment.active == true"
            @change="toggleActivity"
          />
          <label for="activeBtn">Active</label>
          <input
            v-model="statusEquipment.active"
            type="radio"
            id="inactiveBtn"
            :value="false"
            :checked="equipment.active == false"
            @change="toggleActivity"
          />
          <label for="inactiveBtn">Inactive</label> <br />
        </span>
          <span v-show="statusChange" class="label">Note on why changing active status for this piece of equipment: </span> 

          <input class="userInput"
            type="text"
            v-model="statusEquipment.activeNotes"
            v-show="statusChange"
          />
          <button v-show="statusChange" @click="saveActiveStatus">
            Submit
          </button>
          <message-modal :message="message" :type="type" v-if="isModalVisible" @close="closeModal" />

      </form>
      <div class="detail-display">
        <label for="label">Serial Number: </label>
        <span> {{ equipment.serialNumber }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Model: </label>
        <span> {{ equipment.model }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Name: </label>
        <span> {{ equipment.name }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Current Hours: </label>
        <span> {{ equipment.startingHours }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Currently Active: </label>
        <span> {{ equipment.active ? "Yes" : "No" }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Notes about Active Status: </label>
        <span> {{ equipment.activeNotes }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Notes: </label>
        <span id="notes"> {{ equipment.notes }}</span>
      </div>

      <div
        v-for="maintenanceTicket in maintenanceTickets"
        v-bind:key="maintenanceTicket.maintenanceTicketId"
      >
        <hr />
        <span class="label">Maintenance Performed</span> <br />
        <span class="label">Description: </span>
        {{ maintenanceTicket.description }} <br />
        <span class="label">Performed By: </span
        >{{ maintenanceTicket.performedBy }} <br />
        <span class="label">Notes: </span> <br />
        {{ maintenanceTicket.notes }}
      </div>
      <div class="detail-display" v-if="isManager && isArchived">
        <label for="label">Notes about Archive Status: </label>
        <span> {{ equipment.archivedNotes }}</span>
      </div>
      <button v-if="!equipment.archived && isManager" id="archive-equipment" @click="toggleAttemptArchive" class="button">Archive?</button>
      <div v-if="archiveAttempted && isManager">
        <span class="label">Note on why archiving this piece of equipment: </span> 
        <input class="userInput" type="text" id="archiveNotes" v-model="archiveEquipment.archivedNotes" placeholder="Enter notes here" autofocus /> <br>
        <button id="archive-equipment" @click="archivePieceOfEquipment" class="button" :disabled="!archiveEquipment.archivedNotes" >Archive</button>
        <message-modal :message="message" :type="type" v-if="isModalVisible" @close="closeModal" />

      </div>
    </div>
  </div>
</template>

<script>
import EquipmentService from "../../services/EquipmentService";
import MessageModal from '../../components/MODAL/MessageModal.vue';

export default {
  components: {
  MessageModal
},
  data() {
    return {
      message: "",
            type: "EQUIPMENT",
            isModalVisible: false,
      archiveAttempted: false,
      archiveEquipment: {
        equipmentId: 0,
        active: true,
        archived: false,
        archivedNotes: "",
      },
      statusEquipment: {
        equipmentId: 0,
        active: true,
        activeNotes: "",
        updatedByUserId: "",
      },
      maintenanceTickets: [],

      statusChange: false,

      archiveStatusChange: false,
    };
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
    isArchived() {
      return this.equipment.archived;
    },
  },
  props: {
    equipment: {
      type: Object,
      required: true,
    },
  },
  mounted() {
    this.getEquipment();
    this.getEquipmentMaintenanceTickets();
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      
      this.isModalVisible = false;
      this.$router.push({ name: "equipmentList" });

    },
    getEquipment() {
      EquipmentService.getEquipmentById(this.$route.params.equipmentId).then(
        (response) => {
          this.statusEquipment.equipmentId = response.data.equipmentId;
          this.statusEquipment.active = response.data.active;
          this.archiveEquipment.equipmentId = response.data.equipmentId;
          this.archiveEquipment.active = response.data.active;
          this.archiveEquipment.archived = response.data.archived;

        }
      );
    },
    toggleActivity() {
      this.statusChange = this.statusEquipment.active !== this.equipment.active;
    },
    toggleArchiveActivity() {
      this.archiveStatusChange =
        this.archiveEquipment.archived !== this.equipment.archived;
    },
  
    saveActiveStatus() {
      EquipmentService.updateEquipmentActivity(this.statusEquipment)
        .then(() => {
          this.message = "Successfully Changed Status"
         this.showModal();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    archivePieceOfEquipment() {
      this.archiveEquipment.active = false;
      this.archiveEquipment.archived = true;
      EquipmentService.archiveEquipment(this.archiveEquipment)
      .then(() => {
        this.message= "Successfully Archived"
       this.showModal();
      }).catch(() => {
        console.log("Error archiving the piece of equipment.");
      });
    },
    getEquipmentMaintenanceTickets() {
      EquipmentService.getEquipmentMaintenanceById(
        this.$route.params.equipmentId
      ).then((response) => {
        this.maintenanceTickets = response.data;
      });
    },
    toggleAttemptArchive() {
      this.archiveAttempted = !this.archiveAttempted;
    }
  },
};
</script>

<style scoped>
label {
  font-weight: normal;
  font-size: 0.8em;
}

.detail-display span {
  display: block;
  margin-bottom: 10px;
}

.content {
  width: 100%;
  box-sizing: border-box;
}
.archiveNotes {
  margin: 15px 0px;
}
button {
    width: 100%;
    height: 4em;
}
.label {
  font-size: 0.8em;
}

.document-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.userInput{
  margin-bottom: 10px;
    width: 100%;
    box-sizing: border-box;
    height: 4em;
}
form {
  gap: 10px;
}

</style>