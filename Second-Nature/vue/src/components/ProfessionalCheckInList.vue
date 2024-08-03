<template>

  <div>
    <div class="container">
      <div
        class="checkInCard"
        v-for="professionalCheckIn in professionalCheckIns"
        v-bind:key="professionalCheckIn.id"
      >
      <div class="check-in-details">
        <p class="performed">Performed On: <span>{{ professionalCheckIn.date }}</span> </p>
        <button @click="showCheckInDetails(professionalCheckIn.checkInId,  professionalCheckIn.managerId)" class="view-details-btn">View Details</button>
      </div>
       
    </div>
    </div>
  </div>
  
</template>
  
  <script>
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";

export default {
  data() {
    return {
      professionalCheckIns: [],
      managerId: ''

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
  },
  methods: {
    showCheckInDetails(id, managerId){
      this.$router.push({path: `/check-in-details/${id}`, query: { managerId }});
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