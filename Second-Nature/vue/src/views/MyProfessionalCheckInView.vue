<template>
  <div class="container">
    <div class="user">
      <p v-if="!isManager">
        Professional Check Ins for {{ $store.state.user.firstName }}
        {{ $store.state.user.lastName }}
      </p>
      <p v-if="isManager">
        Professional Check Ins Done By: {{ $store.state.user.firstName }}
        {{ $store.state.user.lastName }}
      </p>
    </div>
    <div class="newCheckIn">
      <router-link  v-if="isManager" v-bind:to="{ name: 'newProfessionalCheckIn'}" id="add-check-in-btn">New Check In</router-link>

    </div>

    <div class="check-in-list">
      <professional-check-in-list></professional-check-in-list>
    </div>
  </div>
</template>

<script>
import ProfessionalCheckInList from "../components/ProfessionalCheckInList.vue";
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";
export default {
  data() {
    return {
      userName: "",
    };
  },
  components: {
    ProfessionalCheckInList,
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
  },
};
</script>

<style scoped>
.container {
  display: grid;
  padding: 20px;
}
.user {
  padding-bottom: 20px;
}

</style>