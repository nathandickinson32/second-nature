<template>
    <span id="headder">
       <img src="src\assets\SecondNatureLogo001.png" alt="logo" class="logo"  />
       <div v-if="$store.state.token !=''"> {{  }}</div>
    </span>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
</template>

<script>

</script>

<style>
.logo {
  width: 100px;
  height: auto;
}

</style>