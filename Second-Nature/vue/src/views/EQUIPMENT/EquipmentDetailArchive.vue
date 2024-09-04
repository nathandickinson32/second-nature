<template>
  <div class="content">
    <div class="large-container">
    Form to modify if a piece of equipment is archived or not
      <form id="equipment-form" @submit.prevent="onSubmit">
        <div>
          <h3>Modify the Archive Status for: {{ equipment.name }} ; {{ equipment.model }}</h3>
        </div>
        <div class="form-input-group">
          <label for="userSelect">Equipment is Archived: </label>
          <select v-model="archivedEquipment.archived" id="archived" required>
              <option v-for="option in options" :key="option.Id" :value="option">
                  {{ option }}
              </option>
          </select>
          <input type="text" v-model="archivedEquipment.archivedNotes" />

        </div>
      </form>
    </div>
    <div class="button-section">
      <label @click="goBack" class="clickable-label">Back to Equipment Details</label>
      <span class="separator"> | </span>
      <label @click="saveArchiveStatus" class="clickable-label">Save Archive Status Changes</label>
    </div>
  </div>
</template>

<script>
import EquipmentService from '../../services/EquipmentService';    

export default {
  data() {
    return {
      archivedEquipment: {
          equipmentId: 0,
          updatedByUserId: '',
          archived: '',
          archivedNotes: ''
      },
      equipment: {
          equipmentId: -1,
          serialNumber: '',
          model: '',
          name: '',
          startingHours: '',
          enteredByUserId: '',
          notes: '',
          active: '',
          activeNotes: '',
          archived: false
      },
      options: {
        true: 'true',
        false: 'false'
      }
    }
  },
  props: {
      equipmentId: {
          type: Number,
          required: true
      }
  },
  mounted() {
    this.getEquipment();
  },
  methods: {
    getEquipment(){
      EquipmentService.getEquipmentById(this.$route.params.equipmentId)
        .then((response) => {
          this.equipment = response.data;
          this.archivedEquipment.equipmentId = this.equipment.equipmentId;
          this.archivedEquipment.archived = this.equipment.archived;
        })
    },
    goBack() {
      this.$router.push({ name: 'equipment-detail2', params: { equipmentId: this.equipmentId } });
    },
    saveArchiveStatus() {
      EquipmentService.archiveEquipment(this.archivedEquipment)
      .then(response => {
         console.log(response.data);
         alert('Equipment Archive Status updated!');
         this.$router.push({ name: 'equipmentList' });
       })
       .catch(error => {
         console.log(error);
       });
    }
  }
}
</script>

<style>

</style>