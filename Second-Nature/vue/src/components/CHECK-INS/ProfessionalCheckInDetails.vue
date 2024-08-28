<template>
  <div class="check-in">
    <div class="large-container">
    <p class="label">
      Professional Check In 
      <span class="date">
        {{ professionalCheckIn.date }}
    
      </span>
    </p>
    <div class="label">
     <div v-if="!isManager">Performed By:</div> 
     <div v-if="!isManager"> {{ managerName }}</div> 
    </div>
      <div class="label">
     <div v-if="isManager">Check In With </div> 
     
     <div v-if="isManager">{{ employeeName }} </div> 
      </div>
      <label>
        Check In Notes <br/>
        <div class="notes">
          {{ professionalCheckIn.notes }}
        </div>
      </label>
      
  </div>


  </div>

</template>

<script>
import ProfessionalCheckInService from "../../services/ProfessionalCheckInService";
export default {
  props: {
    professionalCheckIn: {
      type: Object,
      required: true,
    },
    managerName: {
      type: String,
      required: true,
    },
    employeeName: {
      type: String,
      required: true,
    },
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
  },
};
</script>

<style scoped>
.label {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
.notes {
  border: 1px;
  border-style: solid;
  border-color: black;
  padding: 50px 50px 50px 50px;
  text-align: center;
}
.check-in {
  margin-left: 10px;
  margin-right: 10px;
}
</style>