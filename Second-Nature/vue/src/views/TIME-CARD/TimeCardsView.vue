<template>
  <div class="content">
    <h1>Time Cards</h1>
    <time-card-list-vue />
  </div>
</template>

<script>
import TimeCardListVue from "../../components/TIME-CARD/TimeCardList.vue";

export default {
  components: {
    TimeCardListVue,
  },
};
</script>

<style scoped>
.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}
</style>