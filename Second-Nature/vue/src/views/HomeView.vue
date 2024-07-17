<template>
  <div class="home">
    <h1>Second Nature</h1>
    <p>Welcome, {{ this.$store.state.user.firstName }}!</p>
    <p>App Squares go here</p>
    <!-- <div >Instead of having two landing pages for employees vs managers, 
      What if we had the buttons for the manager pages tagged with v-show
    </div> -->
    <div v-show="isManager">Review requests off</div>
    
    <div id="menu-grid">
      <span>testing</span>
      <span>testing</span>
      <span>testing</span>
      <span>testing</span>
      <div>test</div>
      <div>test</div>
      <div>test</div>
      <div>test</div>
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