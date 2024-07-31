<template>
  <div class="kudos-list">
    <span class="filter-section">        
      <label @click="showAllKudos" class="clickable-label">Show All</label>
      <span class="separator"> | </span>
      <label @click="showMyKudos" class="clickable-label">My Kudos</label>
      <span class="separator"> | </span>
      <label @click="showSentKudos" class="clickable-label">Sent Kudos</label>
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
.filter-section {
    display: inline-flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
}
.clickable-label:hover {
    cursor: pointer;
    text-decoration: solid underline;
}
.clickable-label {
    margin: 0px 10px;
}
.sepparator {
    color:cornflowerblue;
}

</style>