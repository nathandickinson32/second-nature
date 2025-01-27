<template>
  <div class="content">

    <div class="document-container">
      <h4>Create Maintenance Ticket</h4>
      <form v-on:submit.prevent="createMaintenanceTicket">
        <div class="equipment-name-serial">
          <span class="label">Name: {{ selectedEquipment.name }} </span> <br />
          <span class="label">Model: {{ selectedEquipment.model }}</span> <br />
          <span class="label"
            >Serial Number: {{ selectedEquipment.serialNumber }}</span
          >
        </div>

        <label for="hours">Current Hours</label>
        <input
          type="text"
          name="hours"
          id="hours"
          :placeholder="placeholders.hours"
          v-model="createMaintenanceTicketDto.hours"
        />

        <label for="notes">Notes</label>
        <textarea
          name="notes"
          id="notes"
          v-model="createMaintenanceTicketDto.notes"
        ></textarea>

        <div class="checkbox-div">
          <input
            type="checkbox"
            name="complete"
            id="complete"
            v-model="createMaintenanceTicketDto.complete"
          />
          <label for="complete"> Complete</label>
        </div>

        <div
          class="maintenance-performed"
          v-for="(
            performed, index
          ) in createMaintenanceTicketDto.createMaintenancePerformedDto"
          v-bind:key="index"
        >
          <h4>Maintenance Performed:</h4>
          <input
            type="text"
            name="maintenance-performed-description"
            id=""
            placeholder="What is being done?"
            v-model="performed.description"
            required
          />
          <input
            type="text"
            name="maintenance-performed-by"
            id=""
            placeholder="Who is doing the work?"
            v-model="performed.performedBy"
            required
          />
          <textarea
            name="maintenance-performed-notes"
            id="maintenance-performed-notes"
            v-model="performed.notes"
            required
          ></textarea>
        </div>
        <button v-on:click.prevent="addMaintenancePerformed">
          Add another maintenance performed
        </button>
        <button v-on:click.prevent="subtractMaintenancePerformed">
          Remove last maintenance performed
        </button>
        
        <input type="submit" value="Create Ticket" id="submitTicket" />
        <message-modal
          :message="message"
          :type="type"
          v-if="isModalVisible"
          @close="closeModal"
        />
      </form>
    </div>
  </div>
</template>

<script>
import MessageModal from "../../components/MODAL/MessageModal.vue";
import EquipmentService from "../../services/EquipmentService";
import MaintenanceService from "../../services/MaintenanceService";
import router from '../../router';
export default {
  components: {
    MessageModal,
  },
  data() {
    return {
      message: "",
      type: "EQUIPMENT",
      isModalVisible: false,
      placeholders: {
        hours: "",
      },
      createMaintenanceTicketDto: {
        equipmentId: null,
        createMaintenancePerformedDto: [
          {
            description: "",
            performedBy: "",
            notes: "",
          },
        ],
      },
      selectedEquipment: "",
    };
  },
  mounted() {
    this.getEquipment();
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
      if (this.message === "Successfully created") {
        this.$router.push({ name: "maintenance-ticket-List" });
      }
    },
    getEquipment() {
      EquipmentService.getEquipmentById(this.$route.params.equipmentId)
        .then((response) => {
          if (response.status == 200) {
            this.selectedEquipment = response.data;
            this.placeholders.hours = `Current hours: ${
              this.selectedEquipment.startingHours || 0
            }`;
          }
        })
        .catch((error) => {
          console.log("Error getting selected equipment.");
        });
    },
    addMaintenancePerformed() {
      this.createMaintenanceTicketDto.createMaintenancePerformedDto.push({
        description: "",
        performedBy: "",
        notes: "",
      });
    },
    subtractMaintenancePerformed() {
      this.createMaintenanceTicketDto.createMaintenancePerformedDto.pop();
    },
    createMaintenanceTicket() {
      this.createMaintenanceTicketDto.equipmentId =
        this.selectedEquipment.equipmentId;
      MaintenanceService.createMaintenanceTicket(
        this.createMaintenanceTicketDto
      )
        .then((response) => {
          if (response.status == 201) {
            this.message = "Successfully created";
            this.showModal();
          }
        })
        .catch((error) => {
          console.log("Error creating ticket.");
        });
    },
  },
};
</script>

<style scoped>
button {
  width: 100%;
  height: 4em;
  margin-top: 10px;
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
  margin-top: 10px;
}

#equipment-identity {
  max-width: 100%;
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