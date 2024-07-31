<template>
  <div>
    <h1>Culture Kudos</h1>
    <router-link v-bind:to="{ name: 'newKudo'}" id="give-kudos-btn" >Give Kudos</router-link>
    <div class="container">
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
    }
  },
  created() {
  },
  methods: {
    
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