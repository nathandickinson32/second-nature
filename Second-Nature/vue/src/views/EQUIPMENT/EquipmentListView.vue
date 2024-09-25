<template>
  <div class="content">
    <h1>Equipment</h1>
    <router-link v-if="isManager" v-bind:to="{ name: 'create-equipment' }" id="add-new-equipment-button">Add a piece of Equipment</router-link>
    <EquipmentList />
</div>
</template>

<script>
import EquipmentList from '../../components/EQUIPMENT/EquipmentList.vue';

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


<style scoped>
h1 {
  text-align: center;
}

#add-new-equipment-button {
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #2a64bd ;
  border-radius: 10px;
  background-color: white;
  width: 100%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  color: #2a64bd;
}

.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}
</style>