<template>
  <div class="home">
    <h4>Welcome, {{ $store.state.user.firstName }}!</h4>
    <div id="menu-grid">
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
h1 {
  text-align: center;
}

h4 {
  text-align: center;
} 

#menu-grid {
  display: flex;
  gap: 30px;
  flex-wrap: wrap;
  justify-content: baseline;
  width: auto;
  margin: 20px 0;
}

.home {
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin: 20px 5%;
}

.menu-icon {
  display: flex;
  flex-direction: column;
  border: 1px solid #a1af9f;
  width: 150px;
  height: 150px;
  padding: 10px;
  border-radius: 10px;
  background-color: white;
  text-align: center;
  align-items: center;
  justify-content: space-around;
  box-shadow: -2px 2px 4px #a1af9f;
}

.menu-icon img {
  width: 75px;
  margin-bottom: 15px;
}
</style>