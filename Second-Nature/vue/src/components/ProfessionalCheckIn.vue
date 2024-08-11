<template>
  <router-link class="small-container" :to="{name: 'check-in-details', params: { checkInId: professionalCheckIn.checkInId, managerId: professionalCheckIn.managerId, employeeId: professionalCheckIn.employeeId} }">
<!-- <h3>{{ ProfessionalCheckIn.date }}</h3> -->
<div class="check-in-details">
       <span></span> <p class="performed">Performed On: <span>{{ professionalCheckIn.date }}</span> </p>
        
        <p class="employeeName" v-if="isManager">{{ employeeName }}</p>
        <!-- <button @click="showCheckInDetails(professionalCheckIn.checkInId,  professionalCheckIn.managerId)" class="view-details-btn">View Details</button> -->
      </div>
<div></div>
  </router-link>
</template>

<script>
import router from '../router/index.js';
import ProfessionalCheckInService from '../services/ProfessionalCheckInService';
export default {
  data() {
    return {
 
      employeeName: "",
    };
  },
    props: {
        professionalCheckIn: {
            type: Object,
            required: true
        }
    },

    created() {
    this.getUsers(this.professionalCheckIn);
  },
  methods: {
    getUsers(profesionalCheckIn) {
      ProfessionalCheckInService.getUserById(this.professionalCheckIn.managerId)
        .then(
          (response) => {
            console.log('The Manager:')
            console.log(response.data);
            profesionalCheckIn.managerName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
        ProfessionalCheckInService.getUserById(this.professionalCheckIn.employeeId)
        .then(
          (response) => {
            console.log('The Employee:')
            console.log(response.data);
            this.employeeName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
     
    },
    viewProfessionalCheckIn() {
      router.push({
        name: 'professionalCheckInDetails',
        params: {
          professionalCheckInId: this.professionalCheckIn.profesionalCheckInId
        }
      });
    },
    showCheckInDetails(id, managerId, employeeId){
      this.$router.push({path: `/check-in-details/${id}`, query: { managerId, employeeId }});
    }
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  },
}
</script>

<style scoped>

</style>