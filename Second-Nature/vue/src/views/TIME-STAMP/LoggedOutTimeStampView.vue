<template>
    <div class="content">
      
      <div class="document-container">
        <h1>{{ currentTime }}</h1>
      {{ currentDate }}
      <form id="user-id-form" v-on:submit.prevent="submitTimeStamp">
        <div class="form-input-group">
          <label for="numericInput">Enter Your User ID</label>
          <input type="number" id="numericInput" v-model="userIdDto.id" placeholder="Enter ID" inputmode="numeric" 
          pattern="[0-9]*" required autofocus />
        </div>
        <button type="submit">Submit</button>
      </form>
      </div>
     
    </div>
  </template>
  
  <script>
  import TimeCardService from '../../services/TimeCardService';
  import router from '../../router';
  export default {
    data() {
      return {
        userIdDto: {},
        
        currentTime: "",
        currentDate: "",
      };
    },
    methods: {
      submitTimeStamp() {
        if (this.userIdDto.id > 0) {
          TimeCardService.createEmployeeTimeCard(this.userIdDto)
            .then((response) => {
              if (response.status === 201) {
                window.alert("Time Stamp Submitted");
                this.$router.push({ name: "login" });

              }
            })
            .catch((error) => {
              console.error("Error submitting time stamp:", error);
            });
        } else {
          window.alert("Please enter a valid User ID.");
        }
      },
    },
    mounted() {
      // Update time immediately and set interval
      const updateTime = () => {
        const now = new Date();
        this.currentTime = now.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
        this.currentDate = now.toLocaleDateString("en-US", { weekday: "long", month: "short", day: "numeric" });
      };
      updateTime();
      this.timer = setInterval(updateTime, 60000);
    },
    beforeUnmount() {
      clearInterval(this.timer);
    },
  };
  </script>
  
  <style scoped>
  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
  }
  
  .form-input-group {
    display: flex;
    flex-direction: column;
  }
  
  button {
    margin-top: 10px;
    width: 100px;
    height: 40px;
  }
  
  button:hover {
    cursor: pointer;
  }
  </style>
  