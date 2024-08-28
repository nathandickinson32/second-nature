<template>
  <div class="content">
    <KudoDetail :kudo="kudo" :giverUserName="giverUserName" :receiverUserName="receiverUserName"></KudoDetail>
  </div>
</template>

<script>
import KudoDetail from '../components/KUDOS/KudoDetail.vue';
import KudosService from '../services/KudosService';
import LeaveRequestService from '../services/LeaveRequestService';

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
      },
      giverUserName: '',
      receiverUserName: ''
    }
  },
  created() {
    this.getKudo();

  },
  methods: {
    getKudo() {
      KudosService.getKudos(this.$route.params.kudosId)
        .then((response) => {
          this.kudo = response.data
          LeaveRequestService.getUserById(this.kudo.giverUserId)
            .then(
              (response1) => {
                this.giverUserName = response1.data.firstName + ' ' + response1.data.lastName;
              }
            ).catch((error) => {
              console.log(error.response);
            });
          LeaveRequestService.getUserById(this.kudo.receiverUserId)
            .then(
              (resp) => {
                this.receiverUserName = resp.data.firstName + ' ' + resp.data.lastName;
              }
            ).catch((error) => {
              console.log(error.response);
            });
        }
        ).catch((error) => {
          console.log(error.response);
        });
    }
  }
}
</script>

<style scoped></style>