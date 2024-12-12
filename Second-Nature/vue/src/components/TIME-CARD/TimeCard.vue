<template>
  <div class="content-container">
    <div class="minutes-container">
      <p class="minutes">{{ timeCard.totalMinutesWorked }} minutes</p>
    </div>
  </div>
</template>

<script>
import { routeLocationKey } from 'vue-router';
import router from '../../router';
import TimeCardService from '../../services/TimeCardService';
export default {
  props: {
    timeCard: {
      type: Object,
      required: true
    }
  },
  methods: {

  }
}
</script>

<style scoped>
.content-container {
  display: flex;
  justify-content: center;
}

.minutes-container {
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgb(199, 194, 194);
  padding: 10px 15px;
  background-color: white;
  box-sizing: border-box;
  width: 100%;
}

@media (min-width: 400px) {
  .minutes-container {
    max-width: 400px;
  }
}

</style>