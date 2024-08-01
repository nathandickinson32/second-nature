<template>
  <div class="container">
    <div class="kudo">
      <h3>{{ kudo.title }}</h3>
      <p class="kudo-note">{{ kudo.notes }}</p>
      <span class="small-font">From: {{ giverUserName }}</span>
      <span class="small-font">To: {{ receiverUserName }}</span>
      <span class="small-font">Date: {{ kudo.date }}</span>
      
    </div>
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
.container {
  display: flex;
  flex-direction: column;
  margin: 20px 5%;
  align-items: center;
}

.kudo {
  display: flex;
  flex-direction: column;
  justify-content: start;
  border: 1px solid #a1af9f;
  border-radius: 5px;
  padding: 20px;
  width: 90%;
  background-color: white;
  box-shadow: -2px 2px 4px #a1af9f;
}
</style>