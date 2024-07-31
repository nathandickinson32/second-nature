<template>
  <div class="kudos-list">
    <span class="filter-section">
        
        {{ currentUserId }}
      <button @click="showAllKudos">Show All Kudos</button>
      <button @click="showMyKudos">Show My Kudos</button>
      <button @click="showSentKudos">Show Kudos I sent</button>
    </span>
    <div v-if="filteredKudos.length === 0">No kudos have been entered yet.</div>
    <div v-else>
        <Kudo v-for="kudo in filteredKudos" :key="kudo.kudosId" :kudo="kudo"></Kudo>
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
            kudos: [],
            filterType: 'all'
        }
    },
    created() {
        this.getKudos();
    },
    computed: {
        filteredKudos() {
            if (this.filterType === 'all') {
                return this.kudos;
            }
            else if (this.filterType === 'fromMe') {
                return this.kudos.filter(kudo => kudo.giverUserId === this.currentUserId);
            }
            else if (this.filterType === 'toMe') {
                return this.kudos.filter(kudo => kudo.receivingUserId === this.currentUserId);
            }
            return []; // Default return for unexpected filterType
        },
        currentUserId() {
            return this.$store.state.user.userId;
        }
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
        },
        showAllKudos() {
            this.filterType = 'all';
        },
        showMyKudos() {
            this.filterType = 'fromMe';
        },
        showSentKudos() {
            this.filterType = 'toMe';
        }
    }
}
</script>

<style>

</style>