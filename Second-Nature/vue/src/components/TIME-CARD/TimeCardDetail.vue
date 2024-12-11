<template>
  <div class="content">
    <!-- Display total minutes worked for each time card -->
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
    // Access the array of timeCardIds passed in the route parameters
    const timeCardIds = this.$route.params.timeCardIds;

    if (Array.isArray(timeCardIds) && timeCardIds.length > 0) {
      // Fetch time card details for each time card ID in the array
      timeCardIds.forEach((id) => {
        TimeCardService.getTimeCardById(id)
          .then((response) => {
            if (response.status === 200) {
              // Add the time card details to the array
              this.timeCards.push(response.data);
            }
          })
          .catch((error) => {
            console.error("Error fetching time card:", error);
          });
      });
    }
  },
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
