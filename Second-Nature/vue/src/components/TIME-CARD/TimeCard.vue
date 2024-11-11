<template>
    <!-- <div class="small-container"> -->
    <router-link class="small-container" :to="{name: 'time-card-detail' , params: {timeCardId: timeCard.timeCardId}}">
        <h4>Minutes Worked: {{ timeCard.totalMinutesWorked }} minutes</h4>
        <h5>Clock-in: {{ formatDateTime(timeCard.clockInTime) }}</h5>
        <!-- clockOutTime shows up with a random date when 
          time card has only been punched once (i.e. no clock out)-->
        <h5>Clock-out: {{ formatDateTime(timeCard.clockOutTime) }}</h5>
        <h5>{{timeCard.roundedDateTime}}</h5>
    </router-link>
    
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

    methods:{
      formatDateTime(dateTime){
        const date = new Date(dateTime);
        const formattedDate = date.toLocaleDateString('en-us', {
          month: '2-digit',
          day: '2-digit',
          year: 'numeric'
        });
        const formattedTime = date.toLocaleTimeString([], {
          hour: '2-digit',
          minute: '2-digit',
          hour12: true
        });

        return `${formattedDate} at ${formattedTime}`;
      }
    }
    
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