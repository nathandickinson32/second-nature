<template>
  <div class="content">
    <h1>Culture Kudos</h1>
    <router-link v-bind:to="{ name: 'newKudo'}" id="give-kudos-btn">Give Kudos</router-link>
    <KudosList />
  </div>
</template>

<script>
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
  border: 1px solid #2a64bd ;
  border-radius: 10px;
  background-color: white;
  width: 100%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  margin-bottom: 20px;
  color: #2a64bd;
}

.content {
  flex-direction: column;
  align-items: center;
}
</style>