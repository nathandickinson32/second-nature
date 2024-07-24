<template>
    <div id="header">
       <img src="src\assets\SecondNatureLogo001.png" alt="logo" class="logo"  />
       <!-- <h1 v-if="$store.state.token !=''" id="display-name"> {{ $store.state.user.firstName }}</h1> -->
    </div>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <span v-if="$store.state.token != ''"> | </span>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
</template>

<script>

</script>

<style scoped>
.logo {
  width: 175px;
  height: auto;
}
#display-name {
  align-self: center;
  padding-top: 20px;
}

#header {
  display: flex;
  background-color: white;
}

#nav {
  border-top: 10px solid #f4fbf3;
  padding: 5px;
  margin-bottom: 15px;
  background-color: white;
  width: fit-content;
}

</style>