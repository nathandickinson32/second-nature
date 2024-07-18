<template>
  <div class="home">
    <h1>Second Nature</h1>
    <p>Welcome, {{ $store.state.user.firstName }}! (header or home?)</p>
    <p>App Squares go below</p>
    <!-- Instead of having two landing pages for employees vs managers, 
      What if we had the buttons for the manager pages tagged with v-show
    -->
    <div v-show="isManager">Review requests off</div>

    <div id="menu-grid">
      <span>span square</span>
      <span>span square</span>
      <span>span square</span>
      <span>span square</span>
      <div>div square</div>
      <div>div square</div>
      <div>div square</div>
      <div>div square</div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  }
};
</script>

<style scoped>
#menu-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-gap: 10px;
  justify-content: center;
  align-items: center;
}
h1 {
  text-align: center;
}
</style>