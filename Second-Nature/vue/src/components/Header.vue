<template>
    <div id="header">
       <img src="\assets\images\SecondNatureLogo001.png" alt="logo" class="logo"  />
    </div>
    <div id="nav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>

      <span v-if="route.name === 'kudosDetail' || route.name === 'newKudo'"> | </span>
      <router-link v-if="route.name === 'kudosDetail' || route.name === 'newKudo'" v-bind:to="{ name: 'kudos' }">Kudos Board</router-link>

      <span v-if="route.name === 'create-equipment' || route.name === 'equipment-detail'"> | </span>
      <router-link v-if="route.name === 'create-equipment' || route.name === 'equipment-detail'" v-bind:to="{ name: 'equipmentList' }">Equipment List</router-link>

      <span v-if="route.name === 'create-maintenance-ticket' || route.name === 'maintenance-ticket-detail' || route.name === 'maintenance-ticket-modify'"> | </span>
      <router-link v-if="route.name === 'create-maintenance-ticket' || route.name === 'maintenance-ticket-detail' || route.name === 'maintenance-ticket-modify'" v-bind:to="{ name: 'maintenance-ticket-List' }">Maintenance Tickets</router-link>

      <span v-if="route.name === 'clock-in' || route.name === 'clock-out' || route.name === 'meal-in' || route.name === 'meal-out'"> | </span>
      <router-link v-if="route.name === 'clock-in' || route.name === 'clock-out' || route.name === 'meal-in' || route.name === 'meal-out'" v-bind:to="{ name: 'time-stamp' }">Time Stamp</router-link>

      <span> | </span>
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>
    <div id="nav" v-else>
      <router-link v-if="route.name === 'time-stamp'" v-bind:to="{ name: 'home' }">Back</router-link>
    </div>
</template>

<script>
import { useRoute } from 'vue-router';

export default {
  setup() {
    const route = useRoute();
    return { route };
  }
};
</script>

<style scoped>
.logo {
  width: 175px;
  height: auto;
}

#header {
  display: flex;
  background-color: white;
  justify-content: center;
  margin: 0 auto;
}

#nav {
  margin: 10px;
  width: auto;
  text-align: center;
}

</style>