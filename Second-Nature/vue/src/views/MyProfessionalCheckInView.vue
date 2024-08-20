<template>
  <div class="content">
   
    <div class="user">
      <p v-if="!isManager">
        Professional Check Ins for {{ $store.state.user.firstName }}
        {{ $store.state.user.lastName }}
      </p>
      <p v-if="isManager">
        Professional Check Ins by: {{ $store.state.user.firstName }}
        {{ $store.state.user.lastName }}
      </p>
    </div>

    <router-link  v-if="isManager" v-bind:to="{ name: 'newProfessionalCheckIn'}" id="add-check-in-btn">New Check In</router-link>
    <professional-check-in-list />
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
#add-check-in-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #2a64bd ;
  border-radius: 10px;
  background-color: white;
  width: 100%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  color: #2a64bd;
}

.content {
  flex-direction: column;
  align-items: center;
}
/* .check-in-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  margin-top: 20px;
  width: 100%;
} */
/* .container {
  display: grid;
  padding: 20px;
  justify-content: center;
} */
.user {
  padding-bottom: 20px;
}
.newCheckIn {
  text-align: center;
  padding-bottom: 40px;
}

</style>