<template>
  <div class="container">
    <h1>Culture Kudos</h1>
    <router-link v-bind:to="{ name: 'newKudo'}" id="give-kudos-btn">Give Kudos</router-link>
    <KudosList />
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
    margin-top: 0;
}

#give-kudos-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  border-style: solid;
  border-radius: 10px;
  border-width: 1px;
  background-color: white;
  width: 100%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  margin-bottom: 20px;
}

.container {
  display: flex;
  flex-direction: column;
  margin: 20px 5%;
  align-items: center;
}
</style>