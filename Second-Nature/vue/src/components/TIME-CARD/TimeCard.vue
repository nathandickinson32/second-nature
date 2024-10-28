<template>
    <!-- <div class="small-container"> -->
    <router-link class="small-container" :to="{name: 'time-card-detail' , params: {timeCardId: timeCard.timeCardId}}">
        <!-- <h3>Hour Type</h3> -->
        <h3>{{timeCard.hourType}} </h3>
        <!-- <h5>Date and Time</h5> -->
        <h5>{{timeCard.dateTime}}</h5>
    </router-link>
    <!-- </div> -->
</template>

<script>
import router from '../../router/index.js';

export default{
  data(){
    return{

    }
  },
  props: {
    timeCard: {
      type: Object,
      required: true
    }
    },
    // methods: {

    // },
}
</script>

<style scoped>
a {
  text-decoration: none;
  color: #3e4a3d;
}

h3 {
  margin: 0;
}

h5 {
  margin-bottom: 0;
}

</style>