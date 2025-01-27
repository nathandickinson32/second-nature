<template>
  <div class="content">
    <div class="document-container">
      <h4>Add Equipment</h4>
      <form v-on:submit.prevent="createEquipment">
        <label for="serial-number">Type of Equipment: </label>
        <select v-model="createEquipmentDto.typeId" id="serial-number" required>
          <option value="0">Choose Equipment Type</option>
          <option v-for="type in types" :key="type.typeId" :value="type.typeId">
            {{ type.name }}
          </option>
        </select>

        <label for="serial-number">Serial Number: </label>
        <input
          type="text"
          name="serial-number"
          id="serial-number"
          placeholder="Serial Number"
          v-model="createEquipmentDto.serialNumber"
        />

        <label for="model">Model: </label>
        <input
          type="text"
          name="model"
          id="model"
          placeholder="Model Number"
          v-model="createEquipmentDto.model"
        />

        <label for="Name">Name: </label>
        <input
          type="text"
          name="name"
          id="name"
          placeholder="Equipment Name"
          v-model="createEquipmentDto.name"
        />

        <label for="hours">Machine Hours: </label>
        <input
          type="text"
          name="hours"
          id="hours"
          placeholder="Hours on the machine"
          v-model="createEquipmentDto.startingHours"
        />

        <label for="notes">Notes: </label>
        <textarea
          name="notes"
          id="notes"
          v-model="createEquipmentDto.notes"
        ></textarea>

        <div class="checkbox-div">
          <input
            type="checkbox"
            name="active"
            id="active"
            v-model="createEquipmentDto.active"
          />
          <label for="active">Equipment is active. </label>
        </div>

        <label for="active-notes">Activity Notes: </label>
        <textarea
          name="active-notes"
          id="active-notes"
          v-model="createEquipmentDto.activeNotes"
        ></textarea>

        <div class="checkbox-div">
          <input
            type="checkbox"
            name="archive"
            id="archive"
            v-model="createEquipmentDto.archived"
          />
          <label for="archive">Equipment is archived. </label>
        </div>

        <input type="submit" value="Add Equipment" />
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
import EquipmentService from "../../services/EquipmentService";
import TypeService from "../../services/TypeService";
import MessageModal from "../../components/MODAL/MessageModal.vue";

export default {
  components: {
    MessageModal,
  },
  created() {
    this.getTypes();
  },
  data() {
    return {
      message: "",
      type: "EQUIPMENT",
      isModalVisible: false,
      types: [],
      createEquipmentDto: {
        serialNumber: "",
        model: "",
        name: "",
        typeId: 0,
        startingHours: 0,
        notes: "",
        active: true,
        activeNotes: "",
        archived: false,
      },
      equipment: {},
    };
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
      if (this.message === "Successfully created") {
        this.$router.push({ name: "equipmentList" });
      }
    },
    getTypes() {
      TypeService.getAllTypes().then((response) => {
        this.types = response.data;
      });
    },
    createEquipment() {
      console.log("Creating Equipment...");
      EquipmentService.createEquipment(this.createEquipmentDto)
        .then((response) => {
          if (response.status == 201) {
            this.equipment = response.data;
            this.message = "Successfully created";
            this.showModal();
          }
        })
        .catch((error) => {
          this.message = "Error creating equipment.";
          this.showModal();
        });
    },
  },
};
</script>

<style scoped>
form {
  align-items: baseline;
}

input {
  margin-bottom: 10px;
  width: 100%;
  box-sizing: border-box;
}

select {
  width: 100%;
  height: 30px;
  margin-right: 10px;
}

label {
  font-weight: normal;
  font-size: 0.8em;
}

textarea {
  resize: none;
  box-sizing: border-box;
  width: 100%;
  height: 6em;
  margin-bottom: 10px;
}

.content {
  box-sizing: border-box;
  width: 100%;
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

.small-container {
  width: 100%;
}
</style>