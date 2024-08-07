<template>
  <div class="container">
    <p class="label">
      Professional Check In 
      <span class="date">
        {{ professionalCheckIn.date }}
    
      </span>
    </p>
    <p class="label">
      Performed By
      {{ managerName }}
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
.notes{
  border-style: solid;
  border-color: black;
}
</style>