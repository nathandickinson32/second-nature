<template>
    <div class="content">
        <h1>Time Cards</h1>
        <div class="small-container" id="example">
        <!-- <router-link class="small-container" :to="{name: 'time-card-detail' , params: {timeCardId: timeCard.timeCardId}}"> -->
            <h3>Hour Type</h3>
            <!-- {{ timeCard.hour_type }} -->
            <h5>Date and Time</h5>
            <!-- timeCard.date_time -->
        <!-- </router-link> -->
    </div>
    </div>
</template>

<script>
/*
import TimeCard component 
import timecard service
method that pulls all current timecards
ensure the method runs as soon as the page loads 
*/

/*
export default{
    components: {
        TimeCard
    },
    created(){
        this.getTimeCards
    },
    methods:{
        getTimeCards(){

        }
    }
}
*/
</script>

<style scoped>
.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.small-container a {
  text-decoration: none;
  color: #3e4a3d;
}

.small-container h3 {
  margin: 0;
}

.small-container h5 {
  margin-bottom: 0;
}
</style>