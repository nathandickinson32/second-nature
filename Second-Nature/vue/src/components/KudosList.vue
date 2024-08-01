<template>
  <div class="kudos-list">
    <div class="filter-section">        
      <label @click="showAllKudos" class="clickable-label">Show All</label>
      <span class="separator"> | </span>
      <label @click="showMyKudos" class="clickable-label">My Kudos</label>
      <span class="separator"> | </span>
      <label @click="showSentKudos" class="clickable-label">Sent Kudos</label>
    </div>
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
.clickable-label {
    color: #2a64bd;
    text-decoration: none;
}

.clickable-label:hover {
    cursor: pointer;
    text-decoration: underline;
}

.clickable-label:visited {
color: #a1af9f;
}

.filter-section {
    width: 100%;
    text-align: center;
}

.kudo-list {
    display: flex;
    width: auto;
}

.sepparator {
    color:cornflowerblue;
}

</style>