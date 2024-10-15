<template>
    <div class="content">
        <h1>Time Stamp</h1>
        <section></section>
        <div class="small-container" v-if="!isLoggedIn">
          <form id="user-id-form" v-on:submit.prevent="displayTimeCards">
          <div class="form-input-group">
            <label for="user-id-label">Enter Your User ID</label>
            <input type="" id="user-id" required autofocus/>
          </div>
          <button type="submit">submit</button>
        </form>
        </div>
        <form v-else class="small-container" @submit.prevent="submitTimeStamp">
          <div class="time-stamp-options">
            <div class="form-input-group">
              <label for="reason">Hour Type </label>
                    <select id="title" v-model="timeCard.hourType" required>
                        <option value="clock-in">Clock In</option>
                        <option value="meal-in">Meal In</option>
                        <option value="clock-out">Clock Out</option>
                        <option value="meal-out">Meal Out</option>

                    </select>
            </div>
                    
            <!-- <div id="in-options"> -->
              <!-- <router-link v-bind:to="{ name: 'clock-in'}" class="choice-btn" id="clock-in-btn">Clock In</router-link>
              <router-link v-bind:to="{ name: 'meal-in'}" class="choice-btn" id="meal-in-btn">Meal In</router-link> -->
              <!-- <button @:click="setHourType('Clock In')" type="button" class="choice-btn" id="clock-in-btn">Clock In</button>
              <button @:click="setHourType('Meal In')" type="button" class="choice-btn" id="meal-in-btn">Meal In</button> -->

            <!-- </div> -->
            <!-- <div id="out-options"> -->
              <!-- <router-link v-bind:to="{ name: 'clock-out'}" class="choice-btn" id="clock-out-btn">Clock Out</router-link>
              <router-link v-bind:to="{ name: 'meal-out'}" class="choice-btn" id="meal-out-btn">Meal Out</router-link> -->
              <!-- <button @:click="setHourType('Clock Out')" type="button" class="choice-btn" id="clock-in-btn">Clock In</button>
              <button @:click="setHourType('Meal Out')" type="button" class="choice-btn" id="meal-out-btn">Meal Out</button> -->
            <!-- </div> -->
          </div>
          <button type="submit" id="submitTimeBtn">Submit Time Stamp</button>
        </form>
        
    </div>
</template>

<script>

import TimeCardService from '../../services/TimeCardService';

export default{
  data(){
    return{
        timeCard:{
        // "timeCardId": 1,
        // "userId": 1,
        hourType: '',
        // "dateTime": "new Date().toISOString()",
        updatedOnDate: null,
        // "updatedByUserId": 0,
        isArchived: false,
        archivedNotes: null
      },
    }
  },
  computed:{
    isLoggedIn(){
      return this.$store.getters.isLoggedIn;
    }
  },
  methods:{
    // setHourType(type){
    //   this.timeStamp.hourType = type;
    // },
    submitTimeStamp(){
      TimeCardService.createNewTimeCard(this.timeCard)
      .then((response)=> {
        console.log(response);
        if(response.status === 201){
          window.alert('Time Stamp submitted')
          this.$router.push({name: 'home'});
        }
      })
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
  width: 100%;
  align-items: center;
}

#in-options, #out-options{
  display: flex;
  width: 100%;
  /* justify-content: space-around; */
  }

#submitTimeBtn {
    margin-top: 5px;
    width: 200px;
    height: 50px;
  }

#submitTimeBtn:hover{
  cursor:pointer;
}
</style>