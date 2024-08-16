<template>
  <div class="content">
    Form to modify the active status of an equipment here
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
    }
  }
}
</script>

<style>

</style>