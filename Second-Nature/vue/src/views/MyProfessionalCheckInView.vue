<template>
  <div>
<professional-check-in-list></professional-check-in-list>
  </div>
</template>

<script>
import ProfessionalCheckInList from '../components/ProfessionalCheckInList.vue';
import ProfessionalCheckInService from '../services/ProfessionalCheckInService';
export default {
components: {
    ProfessionalCheckInList
},
created(){
    this.fetchCheckIns();
},
methods: {
    fetchCheckIns() {
            ProfessionalCheckInService.getAllProfessionalCheckInByEmployeeUserId()
            .then((response) => {
                this.requests = response.data;
        })
        }
    }
}
</script>

<style scoped>

</style>