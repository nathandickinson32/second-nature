<template>
    <div id="header">
       <img src="src\assets\SecondNatureLogo001.png" alt="logo" class="logo"  />
    </div>
    <div id="nav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <span v-if="route.name === 'kudosDetail' || route.name === 'newKudo'"> | </span>
      <router-link v-if="route.name === 'kudosDetail' || route.name === 'newKudo'" v-bind:to="{ name: 'kudos' }">Kudos Board</router-link>
      <span> | </span>
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
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