<template>
  <div class="home">
    <h1>Second Nature</h1>
    <h4>Welcome, {{ $store.state.user.firstName }}!</h4>
    <!-- Instead of having two landing pages for employees vs managers, 
      What if we had the buttons for the manager pages tagged with v-show
    -->
    <div v-show="isManager">Review requests off</div>

    <div id="menu-grid">
      <span class="menu-icon"><router-link v-bind:to="{ name: 'newPTOrequest' }">New Leave Request</router-link></span>
      <div class="menu-icon"><router-link v-bind:to="{ name: 'myRequests' }">My Leave Requests</router-link></div>
      <span class="menu-icon" v-if="isManager"><router-link v-bind:to="{name: 'reviewLeaveRequests'}">Review Leave Requests</router-link></span>
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
  justify-content: space-around;
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
h4 {
  text-align: center;
  /* ^^ this h4 formatting is probably temporary ^^ */
} 

@media only screen and (min-width: 600px) {
  .menu-icon {
    margin-right: 30px;
    margin-left: 30px;
  }
}

@media only screen and (min-width: 800px) {
  #menu-grid {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 1fr 1fr 1fr;
  }
  .menu-icon {
    margin-right: 50px;
    margin-left: 50px;
    justify-self: center;
  }
}
</style>