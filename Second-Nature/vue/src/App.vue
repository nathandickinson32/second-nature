<template>
  <div id="second-nature-app">
    <Header></Header>    
    <router-view />
  </div>
</template>

<script>
import Header from "../src/components/Header.vue";
export default {
  components: {
    Header
  }
}
</script>

<style>
</style>