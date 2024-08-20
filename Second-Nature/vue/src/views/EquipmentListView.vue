<template>
  <div class="content">
  <div class="large-conatiner">
    <h1 id="equipment-header">Equipment</h1>
    <!-- <router-link v-if="isManager" v-bind:to="{ name: 'equipment-detail' , params: { equipmentId: 0} }" id="add-new-equipment-button">Add a piece of Equipment</router-link> -->
    <router-link v-if="isManager" v-bind:to="{ name: 'create-equipment' }" id="add-new-equipment-button">Add a piece of Equipment</router-link>
    <EquipmentList />
  </div>
</div>
</template>

<script>
import EquipmentList from '../components/EquipmentList.vue';
import EquipmentService from '../services/EquipmentService';

export default {
    components: {
        EquipmentList
    },
    computed: {
        isManager() {
        return this.$store.getters.isManager;
        }
    }
}
</script>


<style>
#equipment-header {
  text-align: center;
}

</style>