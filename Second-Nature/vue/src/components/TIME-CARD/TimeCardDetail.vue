<template>
  <div class="content">
    <!-- Display total minutes worked for each time card -->
    <div>Total Minutes Worked</div>
    <div v-for="(card, index) in timeCards" :key="index">
      <p>Total Minutes Worked for Time Card ID {{ card.timeCardId }}: {{ card.totalMinutesWorked }}</p>
    </div>
  </div>
</template>

<script>
import TimeCardService from "../../services/TimeCardService";

export default {
  data() {
    return {
      timeCards: [], // Store the details of multiple time cards
    };
  },
  mounted() {
    const date = this.$route.params.date;
    this.getTimeCards(date);
  },
  methods: {
    getTimeCards(date) {
      TimeCardService.getTimeCardsByDateAndUser(date).then((response => {
        this.timeCards = response.data;
      }));
    }
  }
};
</script>

<style scoped>
.content {
  padding: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
