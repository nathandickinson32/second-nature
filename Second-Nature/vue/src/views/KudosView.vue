<template>
  <div>
    <h1>Culture Kudos</h1>
    <button id="give-kudos-btn" @click="giveKudos">Give Kudos</button>
    <div class="container">
      <!-- <div class="kudos-card" v-for="kudo in kudos" v-bind:key="kudo.id">
        <h4>{{ kudo.title }} for {{ kudo.receiverUserName }}</h4>
        <p>by {{ kudo.giverUserName }} <button>View</button></p> 
      </div> -->
      <KudosList />
    </div>
  </div>
</template>

<script>
import KudosService from '../services/KudosService';
import LeaveRequestService from '../services/LeaveRequestService';
import KudosList from '../components/KudosList.vue';

export default {
  components: {
    KudosList
    },
  data() {
    return {
      kudos: []
    }
  },
  created() {
    this.getKudos();
  },
  methods: {
    getKudos() {
      KudosService.getAllKudos()
      .then(
        (response) => {
          console.log(response.data);
          this.kudos = response.data;
          // this.kudos.forEach((kudo) => {
          //   LeaveRequestService.getUserById(kudo.giverUserId)
          //  .then(
          //     (response) => {
          //       console.log(response.data);
          //       kudo.giverUserName = response.data.firstName + ' ' + response.data.lastName;
          //     }
          //   );
          //   LeaveRequestService.getUserById(kudo.receiverUserId)
          //   .then(
          //     (response) => {
          //        console.log(response.data);
          //        kudo.receiverUserName = response.data.firstName + ' ' + response.data.lastName;
          //      }
          //   );
          // });
        }
      );
    }
  }
}
</script>

<style scoped>
h1 {
    text-align: center;
}
#give-kudos-btn {
  position: absolute;
  right: 20px;
}
.container {
  margin-top: 50px;
  display: flex;
  justify-content: center;
}
.kudos-card {
  background-color: white;
  border-radius: 5px;
  padding: 10px;
}
button {
  background-color: #f4fbf3;
  border: 1px solid #a1af9f;
  border-radius: 5px;
  padding: 5px;
  margin-left: 10px;
}
</style>