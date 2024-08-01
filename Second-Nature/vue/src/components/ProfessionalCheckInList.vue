<template>
  <div>
    <h1>Professional Check Ins For </h1>
    <div class="container">
      <div
        class="checkInCard"
        v-for="professionalCheckIn in professionalCheckIns"
        v-bind:key="professionalCheckIn.id"
      >
        <p>Performed On: {{ professionalCheckIn.date }}</p>
        <div class="view-details-btn">View Details</div>
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
    };
  },
  created() {
    ProfessionalCheckInService.getAllProfessionalCheckInByEmployeeUserId().then(
      (response) => {
        console.log(response);
        this.professionalCheckIns = response.data;
      }
    );
  },
};
</script>
  
  <style scoped>
h1 {
  text-align: center;
}
.container {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-around;
  gap: 30px;
}
.checkInCard {
  border: 1px solid #a1af9f;
  border-radius: 5px;
  padding: 10px;
  padding-left: 20px;
  margin-bottom: 15px;
  width: 300px;
  background-color: white;
  box-shadow: -2px 2px 4px #a1af9f;
}
</style>