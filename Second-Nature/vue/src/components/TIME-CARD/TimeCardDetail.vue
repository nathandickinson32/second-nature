<template>
  <div class="content">
    <!-- Display total minutes worked for each time card -->
    <ul>
      <li v-for="(card, index) in timeCards" :key="index"> {{ formatDate(card.createdOn) }} Clocked In {{ formatTime(card.clockInTime) }} Clocked Out {{ formatTime(card.clockOutTime) }}</li>
    </ul>
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
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });
    },
    formatTime(timeString){
      if (!timeString) return "N/A";

      const date = new Date(timeString);
      return date.toLocaleTimeString('en-US', {hour: 'numeric', minute: '2-digit', hour12: true, });
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
