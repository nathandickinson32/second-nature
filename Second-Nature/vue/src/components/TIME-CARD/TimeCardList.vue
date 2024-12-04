<template>
  <div class="time-card-list">
    <div v-for="(group, date) in groupedTimeCards" :key="date" class="time-card-group">
      <h3>{{ date }}</h3>
      <time-card v-for="timeCard in group" :key="timeCard.timeCardId" :timeCard="timeCard"/>
    </div>


  </div>
</template>

<script>
import TimeCard from "./TimeCard.vue";
import TimeCardService from "../../services/TimeCardService";

export default {
  components: {
    TimeCard,
  },
  data() {
    return {
      timeCards: [],
    };
  },
  created() {
    this.getTimeCards();
  },
  computed: {
   groupedTimeCards() {
    const groups = {};

    this.timeCards.forEach(
      (timeCard) =>{
        const clockInDate = new Date(timeCard.clockInTime);

        if(!isNaN(clockInDate.getTime())){
          const formattedDate = clockInDate.toISOString().split('T')[0];
          if(!groups[formattedDate]){
            groups[formattedDate] = [];
          }
          if(groups[formattedDate].length < 2)
          groups[formattedDate].push(timeCard);
        }else {
          console.error(`Invalid timestamp value for timeCard with ID: ${timeCard.timeCardId}`);

        }
      }
    );
    return groups;
   }



  },
  methods: {
    getTimeCards() {
      TimeCardService.getTimeCardsForCurrentPayPeriod().then((response) => {
        this.timeCards = response.data;
      });
    },
  },
};
</script>

<style scoped>
.time-card-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  width: 100%;
}

.time-card-group {
  width: 100%;
}

h3 {
  margin-bottom: 10px;
  text-align: center;
}
</style>
