<template>
  <div class="small-container">
    <time-stamp
      v-for="timeStamp in filteredTimeStamps"
      :key="timeStamp.timeStampId"
      :timeCard="timeStamp"
    />
  </div>
</template>

<script>
import TimeStamp from "./TimeStamp.vue";
import TimeCardService from "../../services/TimeCardService";
export default {
  components: {
    TimeStamp,
  },
  data() {
    return {
      timeStamps: [],
      
    };
  },
  created() {
    this.getTimeStamps();
  },
  computed: {
    filteredTimeStamps() {
      const todayAndYesterday = this.timeStamps.filter((card) => {
        return(
        this.checkDate(card.dateTimeIn) === "Today" ||
          this.checkDate(card.dateTimeIn) === "Yesterday" ||
          (card.dateTimeOut &&
            (this.checkDate(card.dateTimeOut) === "Today" ||
              this.checkDate(card.dateTimeOut) === "Yesterday"))
      )
              
      });
      
      return todayAndYesterday.slice(0,2);
    },
  },
  methods: {
    getTimeStamps() {
      TimeCardService.getAllTimeCardsByUserId().then((response) => {
        console.log(response.data);
        this.timeStamps = response.data;
      });
    },

    checkDate(date) {
      const today = new Date();
      const yesterday = new Date();
      yesterday.setDate(today.getDate() - 1);
      const formattedDate = this.formatDate(date);
      const formattedToday = this.formatDate(today);
      const formattedYesterday = this.formatDate(yesterday);
      console.log(formattedToday,formattedYesterday, formattedDate)

      if (formattedDate === formattedToday) {
        return "Today";
      } else if (formattedDate === formattedYesterday) {
        return "Yesterday";
      } else {
        return "Other";
      }
    },
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
  },
};
</script>

<style>
</style>