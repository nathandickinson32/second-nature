<template>
  <div class="kudo-detail">
    <KudoDetail :kudo="kudo" />
    <!-- {{ this.kudo }} -->
  </div>
</template>

<script>
import KudoDetail from '../components/KudoDetail.vue';
import KudosService from '../services/KudosService';
import { useRoute } from 'vue-router';

export default {
    
     components: {
       KudoDetail
     },
     data() {
        return {
            kudo: {
                kudosId: '',
                title: '',
                notes: '',
                giverUserId: '',
                receiverUserId: '',
                date: '',
                archive: ''
            }
        }
     },
     created() {
        // this.kudo.kudosId = this.$route.params.kudosId;
        this.getKudo();
        
     },
     methods: {
        getKudo() {
            KudosService.getKudos(this.$route.params.kudosId)
                .then((response) => {
                        console.log(response.data);
                        this.kudo = response.data;
                        console.log(this.kudo);
                    }
                );
        }
    }
}
</script>

<style scoped>

/*
.kudo-detail {
 display: flex;
    flex-direction: column;
    justify-content: start;
    border: 1px solid #a1af9f;
    border-radius: 5px;
    padding: 10px;
    padding-left: 20px;
    margin-top:10px;
    margin-bottom: 10px;
    width: 300px;
    background-color: white;
    box-shadow: -2px 2px 4px #a1af9f;
}
*/

   
</style>