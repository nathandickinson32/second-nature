<template>
  <router-link class="small-container" :to="{name: 'check-in-details', params: { checkInId: professionalCheckIn.checkInId, managerId: professionalCheckIn.managerId} }">
<!-- <h3>{{ ProfessionalCheckIn.date }}</h3> -->
<div class="check-in-details">
        <p class="performed">Performed On: <span>{{ professionalCheckIn.date }}</span> </p>
        <!-- <button @click="showCheckInDetails(professionalCheckIn.checkInId,  professionalCheckIn.managerId)" class="view-details-btn">View Details</button> -->
      </div>


  </router-link>
</template>

<script>
import router from '../router/index.js';
import ProfessionalCheckInService from '../services/ProfessionalCheckInService';
export default {
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
     
    },
    viewProfessionalCheckIn() {
      router.push({
        name: 'professionalCheckInDetails',
        params: {
          professionalCheckInId: this.professionalCheckIn.profesionalCheckInId
        }
      });
    },
    showCheckInDetails(id, managerId){
      this.$router.push({path: `/check-in-details/${id}`, query: { managerId }});
    }
  },
}
</script>

<style>

</style>