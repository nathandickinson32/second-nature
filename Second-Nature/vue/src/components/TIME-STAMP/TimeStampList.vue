<template>
  <div class="small-container">
    <time-stamp v-for="timeStamp in timeStamps" :key="timeStamp.timeStampId" :timeCard="timeStamp"/>
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
  methods: {
    getTimeStamps() {
      TimeCardService.getAllTimeCardsByUserId().then((response) => {
        console.log(response.data);
        this.timeStamps = response.data;
      });
    },
  },
};
</script>

<style>
</style>