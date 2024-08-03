<template>
  <div class="container">

    <div class="user">Professional Check Ins for {{ $store.state.user.firstName }}   {{ $store.state.user.lastName }}</div>
  <div class="check-in-list">
<professional-check-in-list></professional-check-in-list>
  </div>

  </div>
 
</template>

<script>
import ProfessionalCheckInList from '../components/ProfessionalCheckInList.vue';
import ProfessionalCheckInService from '../services/ProfessionalCheckInService';
export default {
  data(){
    return{
      userName: ''
    }
  },
components: {
    ProfessionalCheckInList
},

}
</script>

<style scoped>
.container{
  display: grid;
  padding: 20px;
}
.user{
  padding-bottom: 20px;
}
</style>