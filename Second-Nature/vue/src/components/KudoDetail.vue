<template>
  <div class="kudo">
    <h1>{{ kudo.title }}</h1>
    <p>Given by: {{ giverUserName }}</p>
    <p>Sent to: {{ receiverUserName }}</p>
    <p>Sent on: {{ kudo.date }}</p>
    <p>Note:</p>
    <p class="kudo-note">{{ kudo.notes }}</p>
  </div>
</template>

<script>
import LeaveRequestService from '../services/LeaveRequestService.js'


export default {
  props: {
        kudo: {
            type: Object,
            required: true
        }
  },
  data() {
      return {
        giverUserName: '',
        receiverUserName: ''
      }
  },
  created(){    
      this.getUsers(this.kudo);
  },
  methods: {
      getUsers(kudo) {
          LeaveRequestService.getUserById(kudo.giverUserId)
          .then(
            (response) => {
              console.log('The giving user:')
              console.log(response.data);
              this.giverUserName = response.data.firstName + ' ' + response.data.lastName;
            }
          );
          LeaveRequestService.getUserById(kudo.receiverUserId)
          .then(
            (response) => {
              console.log('The receiving user:')
                console.log(response.data);
                this.receiverUserName = response.data.firstName + ' ' + response.data.lastName;
              }
          );
      }
  }
}

</script>



<style scoped>
.kudo {
    display: flex;
    flex-direction: column;
    justify-content: start;
    border: 1px solid #a1af9f;
    border-radius: 5px;
    padding: 10px;
    padding-left: 20px;
    margin-top:10px;
    margin-bottom: 10px;
    width: 300px;
    background-color: white;
    box-shadow: -2px 2px 4px #a1af9f;
}

</style>