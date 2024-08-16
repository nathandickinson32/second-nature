<template>
  <div class="content">
    Form to modify if a piece of equipment is archived or not
    <div class="button-section">
                <label @click="goBack" class="clickable-label">Back to Equipment Details</label>
                <span class="separator"> | </span>
                <label @click="saveActiveStatus" class="clickable-label">Save Active Status Changes</label>
        </div>
  </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';    

export default {
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
          })
      }
    },
    goBack() {
        this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'detail');
    },
    onSubmit() {
      EquipmentService.updateEquipmentActivity(this.equipmentActivity)
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