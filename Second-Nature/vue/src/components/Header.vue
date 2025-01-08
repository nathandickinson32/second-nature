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

      <span v-if="route.name === 'newProfessionalCheckIn' || route.name === 'check-in-details'"> | </span>
      <router-link v-if="route.name === 'newProfessionalCheckIn' || route.name === 'check-in-details'" v-bind:to="{ name: 'myCheckIns' }">Check In List</router-link>

      <span v-if="route.name === 'reviewLeaveRequests' || route.name === 'newPTOrequest'"> | </span>
      <router-link v-if="route.name === 'reviewLeaveRequests' || route.name === 'newPTOrequest'" v-bind:to="{ name: 'myRequests' }">Back to my List</router-link>

  
      <span> | </span>
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>
    <div id="nav" v-else>
      <router-link v-if="route.name === 'employee-time-stamp'" v-bind:to="{ name: 'home' }">Sign In for Full Access</router-link>
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