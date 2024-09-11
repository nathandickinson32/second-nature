<template>
  <div class="large-container" v-if="kudo.archived === false">
    <h3>{{ kudo.title }}</h3>
    <p class="kudo-note">{{ kudo.notes }}</p>
    <span class="small-font">From: {{ giverUserName }}</span>
    <span class="small-font">To: {{ receiverUserName }}</span>
    <span class="small-font">Date: {{ kudo.date }}</span> <br>
    <button id="show-modal" @click="showModal" v-if="isManager" class="button">Archive</button>
    <Modal v-if="isModalVisible" @close="closeModal"/>
  </div>
</template>

<script>
import KudosService from '../../services/KudosService';
import Modal from '../MODAL/KudoArchive.vue';

export default {
  components: {
    Modal
  },
  data() {
    return {
      isModalVisible: false,
      currentKudo: {},
      isArchived: false,
      archiveNotes: ''
    }
  },
  props: {
    kudo: {
      type: Object,
      required: true
    },
    giverUserName: {
      type: String,
      required: true
    },
    receiverUserName: {
      type: String,
      required: true
    }
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  },
  methods: {
    archiveKudo() {
      this.currentKudo.isArchived = this.isArchived;
      this.currentKudo.archiveNotes = this.archiveNotes;
      KudosService.archiveKudos(this.currentKudo)
      .then(() => {
        window.alert('Kudo archived!');
        this.$router.push({ name: 'kudos' });
      }).catch(() => {
        console.log("Error archiving kudo.");
      });
    },
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
    printStatus() {
      console.log("Status: " + this.isArchived);
    }
  }
}

</script>

<style scoped>
h3 {
  margin-top: 0;
}

#show-modal {
  width: fit-content;
  align-self: center;
}
</style>