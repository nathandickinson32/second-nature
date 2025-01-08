<template>
   
  <div class="small-container">

    
    <router-link :to="{name: 'check-in-details', params: { checkInId: professionalCheckIn.checkInId } }">
    <!-- <h3>{{ ProfessionalCheckIn.date }}</h3> -->
    <p>Performed On: {{ professionalCheckIn.date }}</p>
    <p class="employeeName" v-if="isManager">{{ employeeName }}</p>
            <!-- <button @click="showCheckInDetails(professionalCheckIn.checkInId,  professionalCheckIn.managerId)" class="view-details-btn">View Details</button> -->
  </router-link>
  </div>
 
</template>

<script>
import router from '../../router/index.js';
import ProfessionalCheckInService from '../../services/ProfessionalCheckInService';
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
    getUsers(professionalCheckIn) {
      ProfessionalCheckInService.getUserById(this.professionalCheckIn.managerId)
        .then(
          (response) => {
            professionalCheckIn.managerName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
        ProfessionalCheckInService.getUserById(this.professionalCheckIn.employeeId)
        .then(
          (response) => {
            this.employeeName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
     
    },
    viewProfessionalCheckIn() {
      router.push({
        name: 'professionalCheckInDetails',
        params: {
          professionalCheckInId: this.professionalCheckIn.professionalCheckInId
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
a {
  text-decoration: none;
  color: #3e4a3d;
}

</style>