<template>
  <div class="kudos-list">
    <h1>Culture Kudos</h1>
    <div v-if="kudos.length === 0">No kudos have been entered yet.</div>
    <div v-else>
        <Kudo v-for="kudo in kudos" :key="kudo.kudosId" :kudo="kudo"></Kudo>
    </div>
  </div>
</template>

<script>
import Kudo from './Kudo.vue';
import KudosService from '../services/KudosService';

export default {
    components: {
        Kudo
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
                }
            );
        }
    }
}
</script>

<style>

</style>