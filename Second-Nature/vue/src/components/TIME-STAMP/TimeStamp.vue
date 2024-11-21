<template>
  <div v-if="timeCard.dateTimeOut">
    <h3>{{ formatTime(timeCard.dateTimeOut) }}</h3>
    <h3>Clocked Out ({{ checkDate(timeCard.dateTimeOut) }})</h3>
  </div>
  <div>
    <h3>{{ formatTime(timeCard.dateTimeIn) }}</h3>
    <h3>Clocked In ({{ checkDate(timeCard.dateTimeIn) }})</h3>
  </div>
 
  
</template>

<script>
export default {

  created() {
    console.log(this.timeCard.dateTimeIn);
  },
  props: {
    timeCard: {
      type: Object,
      required: true,
    },
  
  },
  methods: {
    formatTime(dateTime) {
      const date = new Date(dateTime);
      const formattedTime = date.toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
        hour12: true,
      });

      return `${formattedTime}`;
    },
    formatDate(dateTime) {
      const date = new Date(dateTime);

      const formattedDate = date.toLocaleDateString("en-us", {
        month: "2-digit",
        day: "2-digit",
        year: "numeric",
      });

      return `${formattedDate}`;
    },

    checkDate(date) {
      const today = new Date();
      const yesterday = new Date();
      yesterday.setDate(today.getDate() - 1);
      const formattedDate = this.formatDate(date);
      const formattedToday = this.formatDate(today);
      const formattedYesterday = this.formatDate(yesterday);

      if (formattedDate === formattedToday) {
        return "Today";
      } else if (formattedDate === formattedYesterday) {
        return "Yesterday";
      } else {
        return "Other";
      }
    },
  },
};
</script>

<style>
</style>