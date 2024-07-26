<template>
  <div class="kudo">
    <h1>{{ kudo.title }} for {{ kudo.receiverUserName }}</h1>
    <span>
      <p>Given by: {{ kudo.giverUserName }}</p>
      <!-- <button @click.prevent="viewKudo(kudo)">View</button> -->
    </span>
  </div>
</template>

<script>
import router from '../router/index.js';
import LeaveRequestService from '../services/LeaveRequestService.js'
import KudoDetail from './KudoDetail.vue';

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
    created(){
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
        viewKudo(kudo){
          router.push({ 
            name: 'kudosDetail',
            params: {
              kudosId: kudo.kudosId
            }
          });
        }
    },
    computed: {
    }
}

</script>



<style scoped>
.kudo {
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