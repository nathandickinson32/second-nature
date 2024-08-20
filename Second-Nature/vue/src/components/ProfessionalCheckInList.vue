<template>
<div class="container">
  <!-- <div v-if="!isManager">
    <div class="checkInContainer">
     <professional-check-in
        class="checkInCard"
        v-for="professionalCheckIn in professionalCheckIns"
        v-bind:key="professionalCheckIn.id"
        :professionalCheckIn="professionalCheckIn"
      />
    </div>
  </div>

  <div v-if="isManager">
    <div class="checkInContainer">
     <professional-check-in
        class="checkInCard"
        v-for="professionalCheckIn in allProfessionalCheckIns"
        v-bind:key="professionalCheckIn.id"
        :professionalCheckIn="professionalCheckIn"
      />
    </div>
  </div> -->
  <div class="checkInList" v-if="isManager">
    <professional-check-in v-for="professionalCheckIn in allProfessionalCheckIns" :key="professionalCheckIn.checkInId" :professionalCheckIn="professionalCheckIn" />
  </div>
  <div class="checkInList" v-else>
    <professional-check-in v-for="professionalCheckIn in professionalCheckIns" :key="professionalCheckIn.checkInId" :professionalCheckIn="professionalCheckIn" />
  </div>
</div>
</template>
  
  <script>
  import ProfessionalCheckIn from "./ProfessionalCheckIn.vue";
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";

export default {
  components: {
    ProfessionalCheckIn
  },
  data() {
    return {
      professionalCheckIns: [],
      managerId: '',
      allProfessionalCheckIns: [],

    };
  },
  created() {
    ProfessionalCheckInService.getAllProfessionalCheckInByEmployeeUserId().then(
      (response) => {
        this.professionalCheckIns = response.data;
        this.managerId = response.data.managerId
      }
    );
    ProfessionalCheckInService.getAllProfessionalCheckInByManagerUserId().then(
      (response) => {
        this.allProfessionalCheckIns = response.data;
      }
    );
  },
  methods: {
    showCheckInDetails(id, managerId){
      this.$router.push({path: `/check-in-details/${id}`, query: { managerId }});
    }
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  }
  
};
</script>

<style scoped>
.checkInList {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    margin-top: 20px;
    width: 100%;
}
.container {
    width: 100%;
    margin: 0;
}

</style>