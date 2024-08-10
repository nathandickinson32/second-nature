<template>
  <div class="large-container">
    <h1>Equipment</h1>
    <router-link v-if="isManager" v-bind:to="{ name: 'addNewEquipment'}" id="add-new-equipment-button">Add a piece of Equipment</router-link>
    <EquipmentList />
  </div>
</template>

<script>
import EquipmentList from '../components/EquipmentList.vue';

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

</style>