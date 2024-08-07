<template>
  <div class="container">
    <p class="label">
      Professional Check In 
      <span class="date">
        {{ professionalCheckIn.date }}
    
      </span>
    </p>
    <p class="label">
     <div v-if="!isManager">Performed By</div> 
     <div v-if="!isManager"> {{ managerName }}</div> 
      </p>
      <p class="label">
     <div v-if="isManager">Check In With </div> 
     <div v-if="isManager">{{ employeeName }} </div> 
      </p>
      <label>
        Check In Notes <br/>
        <div class="notes">
          {{ professionalCheckIn.notes }}
        </div>
      </label>
      
  </div>
</template>

<script>
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";
export default {

props: {
professionalCheckIn: {
  type: Object,
  required: true
},
managerName: {
  type: String,
  required: true
},
employeeName: {
  type: String,
  required: true
},

},
   computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  }
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
.notes{
  border-style: solid;
  border-color: black;
}
</style>