<template>
  <div class="large-container">
      <h3>{{ kudo.title }}</h3>
      <p class="kudo-note">{{ kudo.notes }}</p>
      <span class="small-font">From: {{ giverUserName }}</span>
      <span class="small-font">To: {{ receiverUserName }}</span>
      <span class="small-font">Date: {{ kudo.date }}</span>
    </div>
</template>

<script>

export default {
  props: {
    kudo: {
      type: Object,
      required: true
    },
    giverUserName: {
      type: String,
      required: true
    },
    receiverUserName: {
      type: String,
      required: true
    }
  }
}

</script>

<style scoped>
h3 {
  margin-top: 0;
}
</style>