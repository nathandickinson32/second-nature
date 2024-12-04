<template>
  <div>
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

 }
   

}
</script>

<style>

</style>