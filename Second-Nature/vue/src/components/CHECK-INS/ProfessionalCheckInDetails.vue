<template>
  <div class="content">
    <div class="document-container">
      <h4>Professional Check In Details</h4>
      <span class="label">
        <div v-if="!isManager">Performed By:</div>
        <div v-if="!isManager">{{ managerName }}</div>
      </span>
      <span class="label">
        <div v-if="isManager">Check In With</div>
        <div v-if="isManager">{{ employeeName }}</div>
      </span>
      <span class="label">
        <div>Check In Date: </div>
        <div>{{ professionalCheckIn.date }}</div>
      </span>
      <span class="label">Check In Notes:</span>
      <div class="notes">
        <span>{{ professionalCheckIn.notes }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    professionalCheckIn: {
      type: Object,
      required: true,
    },
    managerName: {
      type: String,
      required: true,
    },
    employeeName: {
      type: String,
      required: true,
    },
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
  },
};
</script>

<style scoped>
.label {
  display: flex;
  justify-content: space-between;
  width: 100%;
  font-size: 0.8em;
  margin-bottom: 10px;
}

.notes {
  border: 1px solid black;
  padding: 10px;
  text-align: left; 
  font-size: 0.9em;
  margin-top: 10px;
  width: 100%;
  box-sizing: border-box;
  
 
}

.document-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

h4 {
  font-size: 1.2em;
  margin-bottom: 15px;
}
</style>
