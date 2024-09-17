<template>
    <div class="content">
        <h1>Time Stamp</h1>
        <div class="small-container" v-if="!isLoggedIn">
          <form id="user-id-form" v-on:submit.prevent="displayTimeCards">
          <div class="form-input-group">
            <label for="user-id-label">Enter Your User ID</label>
            <input type="" id="user-id" required autofocus/>
          </div>
          <button type="submit">submit</button>
        </form>
        </div>
        <div v-else class="time-stamp-options">
        <div id="in-options">
          <router-link v-bind:to="{ name: 'clock-in'}" class="choice-btn" id="clock-in-btn">Clock In</router-link>
          <router-link v-bind:to="{ name: 'meal-in'}" class="choice-btn" id="meal-in-btn">Meal In</router-link>
        </div>
        <div id="out-options">
          <router-link v-bind:to="{ name: 'clock-out'}" class="choice-btn" id="clock-out-btn">Clock Out</router-link>
          <router-link v-bind:to="{ name: 'meal-out'}" class="choice-btn" id="meal-out-btn">Meal Out</router-link>

        </div>
        <!-- <router-link v-bind:to="{ name: 'clock-in'}" class="choice-btn" id="clock-in-btn">Clock In</router-link>
        <router-link v-bind:to="{ name: 'clock-out'}" class="choice-btn" id="clock-out-btn">Clock Out</router-link>
        <router-link v-bind:to="{ name: 'meal-in'}" class="choice-btn" id="meal-in-btn">Meal In</router-link>
        <router-link v-bind:to="{ name: 'meal-out'}" class="choice-btn" id="meal-out-btn">Meal Out</router-link> -->
        </div>
    </div>
</template>

<script>
export default{
  computed:{
    isLoggedIn(){
      return this.$store.getters.isLoggedIn;
    }
  }
}
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
  margin: 2px;
}

.form-input-group{
  display: flex;
  flex-direction: column;
}

.time-stamp-options{
  display: flex;
  flex-direction: column;
  width: 60%;
  align-items: center;
  
}

#in-options, #out-options{
  display: flex;
  width: 70%;
justify-content: space-around;}
</style>