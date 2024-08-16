<template>
  <div class="small-container">
    <div>
      <span class="line-display"> 
        <p id="model" >{{ equipment.model }}</p>
        <p id="status" :class="equipment.active ? 'active' : 'inactive' "> {{ equipment.active ?  'Active' : 'Inactive' }}</p>
      </span>
      <span class="line-display">
        <button @click="maintenenceHistory(equipment)" class="maintenenceHistoryBtn">Maintenence</button>
        <button @click="newMaintenance(equipment)" class="newMaintenencetBtn">New Ticket</button>
        <button @click="detailView(equipment)" class="detailBtn">Details</button>
      </span>
    </div>
  </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';
import router from '../router';

export default {
  data() {
    return{

    }
  },
  props: {
    equipment: {
      type: Object,
      required: true
    }
  },
  methods: {
    maintenenceHistory(equipment) {
      // Need to actually make components for this view
      router.push({ name: 'maintenance-ticket-list', params: { equipmentId: equipment.equipmentId } });
    },
    newMaintenance(equipment) {
      // Need to actually make components for this view
      router.push({ name: 'new-maintenance-ticket', params: { equipmentId: equipment.equipmentId } });
    },
    detailView(equipment) {
      // Need to actually make components for this view
      router.push({ name: 'equipment-detail', params: { equipmentId: equipment.equipmentId } });
    }
  }
}
</script>

<style>

.line-display {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.active{
  color: #3e4a3d;
}
.inactive{
  color: #ff4848;
}

</style>