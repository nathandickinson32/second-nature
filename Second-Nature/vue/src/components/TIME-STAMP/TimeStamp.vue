<template>
  <div v-if="timeCard.dateTimeOut">
    <div class="stamp-display">
      <h3>{{ formatTime(timeCard.dateTimeOut) }}</h3>
      <div class="status">
        <h3>Clocked Out</h3>
        <h3 class="end-text">{{ checkDate(timeCard.dateTimeOut) }}</h3>

      </div>
    </div>
   
  </div>
  <div>
    <div class="stamp-display">
      <h3>{{ formatTime(timeCard.dateTimeIn) }}</h3>
      <div class="status">
        <h3>Clocked In</h3>
        <h3 class="end-text"> {{ checkDate(timeCard.dateTimeIn) }}</h3>

      </div>
   
    </div>
    
  </div>
 
  
</template>

<script>
export default {

 
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
        const daysOfWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    return daysOfWeek[new Date(date).getDay()];
      }
    },
  },
};
</script>

<style scoped>
.stamp-display{
  display: flex;
flex-direction: column;
align-items: flex-start;
width: 100%;
}
.status {
  display: flex;
  justify-content: space-between;
  width: 100%;

}
.end-text {
  margin-left: auto;
  text-align: right;
}
h3 {
  margin: 8px; /* Remove default margins to reduce spacing */
}

</style>