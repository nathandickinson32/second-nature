<template>
  <div id="second-nature-app">
    <Header></Header>    
    <router-view />
    <FooterVue/>
  </div>
</template>

<script>
import Header from "../src/components/Header.vue";
import FooterVue from './components/Footer.vue';

export default {
  components: {
    Header,
    FooterVue
  }
}
</script>

<style>
</style>