<template>
  <div class="content">
    <h4>Welcome, {{ $store.state.user.firstName }}!</h4>
    <div class="menu-grid">
      <span class="menu-icon">
        <img src="/src/images/calendar-plus.png" alt="">
        <router-link v-bind:to="{ name: 'newPTOrequest' }">New Leave Request</router-link>
      </span>
      <div class="menu-icon">
        <img src="/src/images/invite-alt.png" alt="">
        <router-link v-bind:to="{ name: 'myRequests' }">My Leave Requests</router-link>
      </div>
      <span class="menu-icon" v-if="isManager">
        <img src="/src/images/to-do.png" alt="">
        <router-link v-bind:to="{name: 'reviewLeaveRequests'}">Review Leave Requests</router-link>
      </span>
      <div class="menu-icon">
        <img src="/src/images/handshake.png" alt="">
        <router-link v-bind:to="{ name: 'myCheckIns' }">My Check-ins</router-link>
      </div>
     
      <div class="menu-icon">
        <img src="/src/images/megaphone.png" alt="">
        <router-link v-bind:to="{ name: 'kudos' }">Kudos Board</router-link>
      </div>
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
h4 {
  text-align: center;
  margin: 0;
} 

.content {
  flex-direction: column;
}
</style>