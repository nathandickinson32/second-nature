<template>
  <router-link class="kudo" :to="{ name: 'kudosDetail', params: { kudosId: kudo.kudosId } }">
    <h3>{{ kudo.title }}</h3>
    <h5>To </h5>
    <p>{{ kudo.receiverUserName }}</p>
    <span>
      <h5>From </h5>
      <p>{{ kudo.giverUserName }}</p>
    </span>
  </router-link>
</template>

<script>
import router from '../router/index.js';
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

    }
  },
  created() {
    this.getUsers(this.kudo);
  },
  methods: {
    getUsers(kudo) {
      LeaveRequestService.getUserById(kudo.giverUserId)
        .then(
          (response) => {
            console.log('The giving user:')
            console.log(response.data);
            kudo.giverUserName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
      LeaveRequestService.getUserById(kudo.receiverUserId)
        .then(
          (response) => {
            console.log('The receiving user:')
            console.log(response.data);
            kudo.receiverUserName = response.data.firstName + ' ' + response.data.lastName;
          }
        );
    },
    viewKudo() {
      router.push({
        name: 'kudosDetail',
        params: {
          kudosId: this.kudo.kudosId
        }
      });
    }
  },
  computed: {
  }
}

</script>



<style scoped>
a {
  text-decoration: none;
  color: #3e4a3d;
}

h3 {
  margin: 0;
}

h5 {
  margin-bottom: 0;
}

.kudo {
  display: flex;
  flex-direction: column;
  justify-content: start;
  border: 1px solid #a1af9f;
  border-radius: 10px;
  padding: 20px;
  width: 90%;
  max-width: 400px;
  background-color: white;
  box-shadow: -2px 2px 4px #a1af9f;
  text-decoration: none;
}
</style>