<template>
    <div class="content">
        <h1>Time Card</h1>
        <router-link v-bind:to="{ name: 'clock-in'}" class="choice-btn" id="clock-in-btn">Clock In</router-link>
        <router-link v-bind:to="{ name: 'clock-out'}" class="choice-btn" id="clock-out-btn">Clock Out</router-link>
        <router-link v-bind:to="{ name: 'meal-in'}" class="choice-btn" id="meal-in-btn">Meal In</router-link>
        <router-link v-bind:to="{ name: 'meal-out'}" class="choice-btn" id="meal-out-btn">Meal Out</router-link>
    </div>
</template>

<script>

</script>

<style scoped>
.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.choice-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #2a64bd ;
  border-radius: 10px;
  background-color: white;
  width: 100%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  color: #2a64bd;
}
</style>