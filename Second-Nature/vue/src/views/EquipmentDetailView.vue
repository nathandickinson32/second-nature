<template>
  <div class="content">
    <!-- {{ view }} -->
      <h1>Equipment Detail</h1>
      <EquipmentDetail v-show="getEquipmentDetailView == 'detail'" v-bind:equipmentId="this.$route.params.equipmentId" />
      <EquipmentModify v-show="getEquipmentDetailView == 'modify'" v-bind:equipmentId="this.$route.params.equipmentId" />
      <EquipmentActiveStatusChange v-show="getEquipmentDetailView == 'activeStatus'" v-bind:equipmentId="this.$route.params.equipmentId" />
      <EquipmentDetailArchive v-show="getEquipmentDetailView == 'archive'" v-bind:equipmentId="this.$route.params.equipmentId" />
  </div>
</template>

<script>
import EquipmentDetail from '../components/EQUIPMENT/EquipmentDetail.vue';
import EquipmentModify from '../components/EQUIPMENT/EquipmentModify.vue';
import EquipmentService from '../services/EquipmentService';
import EquipmentActiveStatusChange from '../components/EQUIPMENT/EquipmentActiveStatusChange.vue';
import EquipmentDetailArchive from './EquipmentDetailArchive.vue';

export default {
  components: {
    EquipmentDetail,
    EquipmentModify,
    EquipmentActiveStatusChange,
    EquipmentDetailArchive
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
h1 {
  text-align: center;
}

.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}
</style>