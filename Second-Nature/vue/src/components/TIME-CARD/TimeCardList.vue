<template>
  <div class="">
    <div class="time-card-list">
      <time-card
        v-for="timeCard in timeCards"
        :key="timeCard.timeCardId"
        :timeCard="timeCard"
      />
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

  methods: {
    getTimeCards() {
      TimeCardService.getTimeCardsForCurrentPayPeriod().then((response) => {
        this.timeCards = response.data;
      });
    },
  },
};
</script>

<style>
</style>