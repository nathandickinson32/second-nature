<template>
  <div class="content">
    <h4>Welcome, {{ $store.state.user.firstName }}!</h4>
    <div class="menu-grid">
      <div class="menu-icon">
        <!-- need to find unique time card icon that 
       matches the other icons stylistically -->
        <img src="/src/images/clock.png" alt="" />
        <router-link v-bind:to="{ name: 'time-stamp' }">Time Stamp</router-link>
      </div>
      <div class="menu-icon">
        <!-- need to find unique time card icon that 
       matches the other icons stylistically -->
        <img src="/src/images/to-do.png" alt="" />
        <router-link v-bind:to="{ name: 'time-card' }">Time Cards</router-link>
      </div>

     
      <div class="menu-icon">
        <img src="/src/images/invite-alt.png" alt="" />
        <router-link v-bind:to="{ name: 'myRequests' }"
          >Leave Requests</router-link
        >
      </div>
   
      <div class="menu-icon">
        <img src="/src/images/handshake.png" alt="" />
        <router-link v-bind:to="{ name: 'myCheckIns' }"
          >Check-ins</router-link
        >
      </div>
      <div class="menu-icon">
        <img src="/src/images/megaphone.png" alt="" />
        <router-link v-bind:to="{ name: 'kudos' }">Kudos Board</router-link>
      </div>
      <div class="menu-icon">
        <img src="/src/images/lawnmower.png" alt="" />
        <router-link v-bind:to="{ name: 'equipmentList' }"
          >Equipment</router-link
        >
      </div>
      <div class="menu-icon">
        <img src="/src/images/tools.png" alt="" />
        <router-link v-bind:to="{ name: 'maintenance-ticket-List' }"
          >Maintenance</router-link
        >
      </div>

      <div class="menu-icon">
        <img src="/src/images/cloudy-day.png" alt="" />
        <router-link v-bind:to="{ name: 'weather-view' }"
          >Weather</router-link
        >
      </div>

    </div>
  </div>
</template>

<script>
export default {
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
  },
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