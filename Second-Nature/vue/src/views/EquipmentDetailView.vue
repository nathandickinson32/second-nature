<template>
  <div class="content">
      <h1>Equipment Detail</h1>
      <div v-show="getEquipmentDetailView == 'detail'">
        <EquipmentDetail v-bind:equipmentId="this.$route.params.equipmentId" />
      </div>
      <div v-show="getEquipmentDetailView == 'modify'">
        <EquipmentModify v-bind:equipmentId="this.$route.params.equipmentId" />
      </div>
      <div v-show="getEquipmentDetailView == 'activeStatus'">
        <EquipmentActiveStatusChange v-bind:equipmentId="this.$route.params.equipmentId" />
      </div>
      <div v-show="getEquipmentDetailView == 'archive'">
        <EquipmentModify v-bind:equipmentId="this.$route.params.equipmentId" />
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
    EquipmentDetail,
    EquipmentModify,
    EquipmentActiveStatusChange
  },
  data() {
    return {
      equipment: {
        equipmentId: -1,
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
  mounted() {
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