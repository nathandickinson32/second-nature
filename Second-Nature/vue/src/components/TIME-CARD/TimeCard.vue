<template>
  <div class="content-container">
    <div class="minutes-container">
      <div class="minutes-wrapper">
        <p class="minutes">{{ timeCard.totalMinutesWorked }} minutes</p>
      </div>
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
  background-color: white;
  box-sizing: border-box;
  width: 100%;
  padding: 0 10px;
}

.minutes-wrapper {
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgb(199, 194, 194);
  width: 100%;
  padding: 10px 0;
}

.minutes {
  padding-left: 5px;
}

@media (min-width: 400px) {
  .minutes-container {
    max-width: 400px;
  }
}
</style>