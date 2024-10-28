<template>
    <div class="content">
        <TimeCard v-for="timeCard in timeCardList" v-bind:key="timeCard.id" :timeCard="timeCard"></TimeCard> 
    </div>
</template>

<script>
import TimeCardService from '../../services/TimeCardService';
import TimeCard from './TimeCard.vue'
export default{
    components:{
        TimeCard,
    },
    data(){
        return{
            timeCardList: [],
        };
    },
    created(){
        this.getTimeCards();
    },
    methods: {
        getTimeCards(){
            TimeCardService.getAllTimeCardsByUserId().then((response) => 
            {this.timeCardList = response.data;});
        }
    }

 }
</script>

<style scoped>
.content {
  box-sizing: border-box;
  width: 100%;
  flex-direction: column;
}
</style>