<template>
  <div class="large-container" v-if="kudo.archived === false">
    <h3>{{ kudo.title }}</h3>
    <p class="kudo-note">{{ kudo.notes }}</p>
    <span class="small-font">From: {{ giverUserName }}</span>
    <span class="small-font">To: {{ receiverUserName }}</span>
    <span class="small-font">Date: {{ kudo.date }}</span> <br>
    <button id="archive-kudo" @click="archiveKudo" v-if="isManager" class="button">Archive</button>
  </div>
</template>

<script>
import KudosService from '../../services/KudosService';

export default {
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
      KudosService.archiveKudos(this.kudo)
      .then(() => {
        window.alert('Kudo archived!');
        this.$router.push({ name: 'kudos' });
      }).catch(() => {
        console.log("Error archiving kudo.");
      });
    }
  }
}

</script>

<style scoped>
h3 {
  margin-top: 0;
}

#archive-kudo {
  width: fit-content;
  align-self: center;
}
</style>