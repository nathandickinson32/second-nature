<template>
  <div class="content">
      <h1>Equipment Detail</h1>
      <div v-if="view == 'detail'">
        <EquipmentDetail v-bind:equipmentId="equipmentId" />
      </div>
      <div v-else-if="view == 'modify'">
        <EquipmentModify v-bind:equipmentId="equipmentId" />
      </div>
      <div v-else-if="view == 'activeStatus'">
        <EquipmentActiveStatusChange v-bind:equipmentId="equipmentId" />
      </div>
      <div v-else-if="view == 'archive'">
        <EquipmentModify v-bind:equipmentId="equipmentId" />
      </div>
  </div>
</template>

<script>
import EquipmentDetail from '../components/EquipmentDetail.vue';
import EquipmentModify from '../components/EquipmentModify.vue';
import EquipmentService from '../services/EquipmentService';
import EquipmentActiveStatusChange from '../components/EquipmentActiveStatusChange.vue';

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
      },
      view: 'detail'
    }
  },
  computed: {
    getEquipmentDetailView() {
      return this.$store.getters.getEquipmentDetailView;
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

<style scoped>
.content {
  flex-direction: column;
}
h1 {
  text-align: center;
}

</style>