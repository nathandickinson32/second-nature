<template>

  <div v-if="!isManager">
    <div class="large-container">
     <professional-check-in
        class="checkInCard"
        v-for="professionalCheckIn in professionalCheckIns"
        v-bind:key="professionalCheckIn.id"
        :professionalCheckIn="professionalCheckIn"
      >
   
       
    </professional-check-in>
    </div>
  </div>

  <div v-if="isManager">
    <div class="large-container">
     <professional-check-in
        class="checkInCard"
        v-for="professionalCheckIn in allProfessionalCheckIns"
        v-bind:key="professionalCheckIn.id"
        :professionalCheckIn="professionalCheckIn"
      >
   
       
    </professional-check-in>
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
        console.log(response);
        this.professionalCheckIns = response.data;
        this.managerId = response.data.managerId
      }
    );
    ProfessionalCheckInService.getAllProfessionalCheckInByManagerUserId().then(
      (response) => {
        console.log(response);
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


.checkInCard {
  border: 1px solid #a1af9f;
  border-radius: 5px;
  padding: 10px;
  padding-left: 20px;
  margin-bottom: 15px;
  width: 350px;
  background-color: white;
  box-shadow: -2px 2px 4px #a1af9f;
}
.check-in-details{
  display: flex;
justify-content: space-between;

}
.view-details-btn{
  padding: 5px 5px 5px 5px;
  margin-top: 50px;
  
  
}

</style>