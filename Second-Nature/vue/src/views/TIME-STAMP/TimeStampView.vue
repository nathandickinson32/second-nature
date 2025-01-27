<template>
    <div class="content">
       <h1>{{ currentTime }} </h1>
        {{ currentDate }}
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
        
          <button type="submit" id="submitTimeBtn">Punch</button>
          <message-modal
          :message="message"
          :type="type"
          v-if="isModalVisible"
          @close="closeModal"
        />
        </form>
        <time-stamp-list/>
    </div>
</template>

<script>
import MessageModal from "../../components/MODAL/MessageModal.vue";

import TimeCardService from '../../services/TimeCardService';
import TimeStampList from '../../components/TIME-STAMP/TimeStampList.vue';

export default{
  components: {
    TimeStampList,
    MessageModal
  },
  data(){
    return{
      message:"Time Stamp Submitted",
      type: "TIME STAMP",
      isModalVisible: false,
      
      currentTime: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
      currentDate: new Date().toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' }),
    
    }
      
    
  },
  computed:{
    isLoggedIn(){
      return this.$store.getters.isLoggedIn;
    },
  },
  methods:{
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
      this.$router.push({ name: "home" });
    },
    
    submitTimeStamp(){
        TimeCardService.createNewTimeCard(this.timeCard)
      .then((response)=> {
        if(response.status === 201){
         this.showModal();
        }
      })
  },
  mounted() {
    this.timer = setInterval(() => {
      this.currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
      this.currentDate = new Date().toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric' })
    }, 60000) 
  },
  beforeUnmount() {
    clearInterval(this.timer)
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