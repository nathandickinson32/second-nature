<template>
  <div class="kudo">
    <h1>{{ kudos.title }}</h1>
    <p>Given by: {{ giverUserName }}</p>
    <p>Sent to: {{ receiverUserName }}</p>
    <p>Sent on: {{ kudo.date }}</p>
    <p>Note:</p>
    <p class="kudo-note">{{ kudo.notes }}</p>
  </div>
</template>

<script>
import LeaveRequestService from '../services/LeaveRequestService.js'
import KudosService from '../services/KudosService.js';

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
      receiverUserName: '',
      kudos: {}
    }
  },
  created() {
    this.getKudo();
  },
  methods: {
    getKudo() {
      KudosService.getKudos(this.kudo.kudosId)
        .then((response) => {
          this.kudos = response.data;
          console.log("[Kudos Detail View] Kudos ID: " + response.data.kudosId);

          this.getUsers();
        }
        );
    },
    getUsers() {
      console.log("[Kudos Detail] Kudos ID: " + this.kudos.kudosId);
      LeaveRequestService.getUserById(this.kudos.giverUserId)
        .then(
          (response) => {
            this.giverUserName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
      LeaveRequestService.getUserById(this.kudos.receiverUserId)
        .then(
          (response1) => {
            console.log(response1.data);
            this.receiverUserName = response1.data.firstName + ' ' + response1.data.lastName;
          }
        );
    },
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
  margin-top: 10px;
  margin-bottom: 10px;
  width: 300px;
  background-color: white;
  box-shadow: -2px 2px 4px #a1af9f;
}
</style>