<template>
  <div class="small-container">
    <div>
      <span> 
        <p id="model" >{{ equipment.model }}</p>
        <p id="status"> {{ equipment.isActive ?  'Active' : 'Inactive' }}</p>
      </span>
      <span>
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
      // Need to actually make this view & components
      router.push({ name: 'maintenance-ticket-list', params: { equipmentId: equipment.equipmentId } });
    },
    newMaintenance(equipment) {
      // Need to actually make this view & components
      router.push({ name: 'new-maintenance-ticket', params: { equipmentId: equipment.equipmentId } });
    },
    detailView(equipment) {
      // Need to actually make this view & components
      router.push({ name: 'equipment-detail', params: { equipmentId: equipment.equipmentId } });
    }
  }
}
</script>

<style>

</style>