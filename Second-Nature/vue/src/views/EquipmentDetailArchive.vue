<template>
  <div class="content">
    Form to modify if a piece of equipment is archived or not
    <form id="equipment-form" @submit.prevent="onSubmit">
      <div class="button-section">
        <label @click="goBack" class="clickable-label">Back to Equipment Details</label>
        <span class="separator"> | </span>
        <label @click="saveArchiveStatus" class="clickable-label">Save Archive Status Changes</label>
      </div>
    </form>
  </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';    

export default {
  data() {
    return {
      archivedEquipment: {
          equipmentId: 0,
          updatedByUserId: '',
          archived: true
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
    }
  },
    props: {
        equipmentId: {
            type: Number,
            required: true
        }
    },
    created() {
    this.getEquipment();
  },
  methods: {
    getEquipment(){
      if(this.$route.params.equipmentId > 0){
        EquipmentService.getEquipmentById(this.$route.params.equipmentId)
          .then((response) => {
            this.equipment = response.data;
            this.archivedEquipment.equipmentId = this.equipment.equipmentId;
            this.archivedEquipment.archived = this.equipment.archived;
          })
      }
    },
    goBack() {
        this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'detail');
    },
    onSubmit() {
      EquipmentService.archiveEquipment(this.equipmentActivity)
      .then(response => {
         console.log(response.data);
         alert('Equipment activity updated!');
         this.$router.push({ name: 'equipment' });
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