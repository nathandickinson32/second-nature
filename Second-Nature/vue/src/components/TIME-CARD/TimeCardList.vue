<template>
  <div class="time-card-list">
    <div v-for="(group, date) in groupedTimeCards" :key="date" class="time-card-group">
      <div class="date-container">
        <div class="date-wrapper">
          <!-- put button in date wrapper for easy formatting -->
          <h3 class="date">{{ date }}</h3>
        </div>

        <div class="button-wrapper" @click="this.$router.push">
          <button class="day-view-button">Day View</button>
        </div>
      </div>
      <time-card v-for="timeCard in group" :key="timeCard.timeCardId" :timeCard="timeCard" />
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
        (timeCard) => {
          const clockInDate = new Date(timeCard.clockInTime);

          if (!isNaN(clockInDate.getTime())) {
            const formattedDate = clockInDate.toISOString().split('T')[0];
            if (!groups[formattedDate]) {
              groups[formattedDate] = [];
            }
            if (groups[formattedDate].length < 2)
              groups[formattedDate].push(timeCard);
          } else {
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

.date {
  margin-bottom: 20px;
  padding-left: 15px;
}

.date-wrapper {
  display: flex;
  justify-content: flex-start;
  box-sizing: border-box;
  width: 100%;
}

.date-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

.day-view-button {
  height: 40px;
  width: 80px;
  margin-right: 15px;
}

.button-wrapper {
  display: flex;
  justify-content: flex-end;
}

@media (min-width: 300px) {
  .date-wrapper {
    max-width: 300px;
  }
}
</style>
