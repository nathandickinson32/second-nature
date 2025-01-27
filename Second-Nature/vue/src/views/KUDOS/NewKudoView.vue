<template>
  <div class="content">
    <h2>Give Kudos</h2>
    <div class="large-container">
      <form id="give-kudos-form" @submit.prevent="onSubmit">
        <!-- Dropdown list of users -->
        <div class="form-input-group">
          <label for="userSelect">To: </label>
          <select v-model="kudo.receiverUserId" id="userSelect" required>
            <option value="" disabled>Select a Team Member</option>
            <option
              v-for="user in users"
              :key="user.userId"
              :value="user.userId"
            >
              {{ user.firstName }} {{ user.lastName }}
            </option>
          </select>
        </div>

        <!-- Title text field -->
        <div class="form-input-group">
          <label for="title">Title: </label>
          <input type="text" v-model="kudo.title" id="title" required />
        </div>

        <!-- Note text field -->
        <div class="form-input-group">
          <textarea v-model="kudo.notes" id="note" rows="4" required></textarea>
        </div>

        <!-- Submit button -->
        <button type="submit">Send this Kudo!</button>
        <message-modal
          :message="message"
          :type="type"
          v-if="isModalVisible"
          @close="closeModal"
        />
      </form>
    </div>
  </div>
</template>

<script>
import KudosService from "../../services/KudosService";
import MessageModal from "../../components/MODAL/MessageModal.vue";

export default {
  components: {
    MessageModal,
  },
  data() {
    return {
      message: "Successfully Created",
      type: "KUDOS",
      isModalVisible: false,

      users: [],
      kudo: {
        title: "",
        notes: "",
        giverUserId: "",
        receiverUserId: "",
        date: "",
        archive: "",
        archivedNotes: "",
      },
      currentDate: new Date().toISOString().slice(0, 10),
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
      this.$router.push({ name: "kudos" });
    },
    fetchUsers() {
      KudosService.getAllOtherUsers()
        .then((response) => {
          this.users = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onSubmit() {
      this.kudo.giverUserId = this.currentUserId;
      this.kudo.date = this.currentDate;
      KudosService.giveKudos(this.kudo)
        .then((response) => {
          this.title = "";
          this.note = "";
          this.selectedUserId = "";
          this.showModal();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    currentUserId() {
      return this.$store.state.user.userId;
    },
  },
};
</script>

<style scoped>
button {
  height: 50px;
  width: 200px;
}

h2 {
  text-align: center;
}

input {
  width: 100%;
}

label {
  margin-right: 10px;
}

#note {
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  width: 100%;
  height: 10em;
  resize: none;
  border-radius: 5px;
}

.content {
  flex-direction: column;
  align-items: center;
}
</style>