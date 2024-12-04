<template>
  <div class="small-container" v-on:click="viewDayDetails">
<h1>{{ timeCard.totalMinutesWorked }}</h1>
  </div>
</template>

<script>
import { routeLocationKey } from 'vue-router';
import router from '../../router';
import TimeCardService from '../../services/TimeCardService';
export default {
  props: {
    timeCard: {
      type: Object,
      required: true
    }
  },
 methods: {
  viewDayDetails(){
    router.push({
      name: 'time-card-detail',
      params: {
        timeCardId: this.timeCard.timeCardId
      }
    })
  }
 }
   

}
</script>

<style>

</style>