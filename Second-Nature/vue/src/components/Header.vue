<template>
    <div id="header">
       <img src="src\assets\SecondNatureLogo001.png" alt="logo" class="logo"  />
    </div>
    <div id="nav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <span> | </span>
      <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>
</template>

<script>

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
  border-top: 10px solid #f4fbf3;
  padding: 5px;
  margin-bottom: 15px;
  background-color: white;
  width: fit-content;
}

</style>