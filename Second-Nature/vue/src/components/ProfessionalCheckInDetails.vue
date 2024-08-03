<template>
  <div class="container">
    <p class="label">
      Professional Check In
      <span class="date">
        {{ this.professionalCheckIn.date }}
      </span>
    </p>
    <p class="label">
      Performed By
      
        
      
    </p>
  </div>
</template>

<script>
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";
export default {
  data() {
    return {
      professionalCheckIn: {
        checkInId: "",
        managerId: "",
        employeeId: "",
        notes: "",
        date: "",
      },
      managerName: "",
     
    };
  },
  created() {
    ProfessionalCheckInService.getProfessionalCheckInByCheckInId(
      this.$route.params.checkInId
    ).then((response) => {
      console.log(response);
      this.professionalCheckIn = response.data;
      
    });
   
  },
};
</script>

<style scoped>
.container {
  padding: 10px;
}
.label {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
</style>