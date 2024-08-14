<template>
  <div class="content">
      <h1>Equipment Detail View</h1>
      <EquipmentDetail v-bind:equipmentId="equipmentId" />
  </div>
</template>

<script>
import EquipmentDetail from '../components/EquipmentDetail.vue';
import EquipmentService from '../services/EquipmentService';

export default {
  components: {
    EquipmentDetail
  },
  data() {
    return {
      equipment: {
        equipmentId: '',
        serialNumber: '',
        model: '',
        name: '',
        startingHours: '',
        enteredByUserId: '',
        notes: '',
        active: true,
        activeNotes: '',
        archived: false
      }
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