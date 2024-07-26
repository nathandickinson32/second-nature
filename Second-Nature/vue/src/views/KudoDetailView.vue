<template>
  <div class="kudo-detail">
    <KudoDetail />
  </div>
</template>

<script>
import KudoDetail from '../components/KudoDetail.vue';
import KudosService from '../services/KudosService';
import { useRoute } from 'vue-router';

export default {
    setup(){
    const route = useRoute()
    console.log( route.params );
    },
     components: {
       KudoDetail
     },
     data() {
        return {
            kudo: {
                kudosId: 0,
                title: '',
                notes: '',
                giverUserId: 0,
                receiverUserId: 0,
                date: ''
            }
        }
     },
     created() {
        this.getKudo();
     },
     methods: {
        getKudo() {
            KudosService.getKudo(this.kudo)
                .then((response) => {
                        console.log(response.data);
                        this.kudo = response.data;
                    }
                );
        }
    }
}
</script>

<style scoped>
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
</style>