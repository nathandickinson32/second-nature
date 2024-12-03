<template>
  <div class="">
    {{ timeCard.totalMinutesWorked }} 
  </div>
</template>

<script>
import TimeCardService from '../../services/TimeCardService';
export default {

data() {
    return {
    timeCard: {}
    }
},
mounted() {
TimeCardService.getTimeCardById(
    this.$route.params.timeCardId
).then(
  (response) => {
    if(response.status==200) {
      this.timeCard = response.data;
    }
  }
).catch((error) => {
        console.log(error);
      });
}
}
</script>

<style>

</style>