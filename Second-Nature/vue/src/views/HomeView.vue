<template>
  <div class="home">
    <h1>Second Nature</h1>
    <p>Welcome, {{ $store.state.user.firstName }}!</p>
    <!-- Instead of having two landing pages for employees vs managers, 
      What if we had the buttons for the manager pages tagged with v-show
    -->
    <div v-show="isManager">Review requests off</div>

    <div id="menu-grid">
      <span class="menu-icon">New Leave Request</span>
      <div class="menu-icon">My Leave Requests</div>
      <span class="menu-icon" v-if="isManager">Review Leave Requests</span>
      <span class="menu-icon">span square</span>
      <span class="menu-icon">span square</span>
      <div class="menu-icon">div square</div>
      <div class="menu-icon">div square</div>
      <div class="menu-icon">div square</div>
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
  display: flex;
  gap: 50px;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-items: center;
}
.menu-icon {
  display: flex;
  border: 1px solid black;
  width: 75px;
  height: 75px;
  padding: 10px;
  border-radius: 5px;
  background-color: white;
  text-align: center;
  align-items: center;
  justify-content: center;
}
h1 {
  text-align: center;
}
p {
  text-align: center;
  /* ^^ this p formatting is probably temporary ^^ */
} 
</style>