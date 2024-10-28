<template>
    <div class="content">
        <h1>Time Cards</h1>
        <!-- <router-link class="small-container" :to="{name: 'time-card-detail' , params: {timeCardId: timeCard.timeCardId}}"> -->
            <!-- <h3>Hour Type</h3> -->
            <!-- {{ timeCard.hour_type }} -->
            <!-- <h5>Date and Time</h5> -->
            <!-- timeCard.date_time -->
            <TimeCardList/>
        <!-- </router-link> -->
    </div>
</template>

<script>
import TimeCardList from '../../components/TIME-CARD/TimeCardList.vue';

export default{
    components: {
        TimeCardList
    },
    
    // methods:{
    //     isManager(){
    //       return this.$store.getters.isManager;
    //     }
    // }
}
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