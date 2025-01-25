<template>
  <div class="content">
    <div class="document-container">
      <h4>Manager Check In</h4>

      <form @submit.prevent="submitCheckIn">
        <span class="label">
          <div for="user-select">Employee:</div>
          <select
            name="user-select"
            id="user-select"
            v-model="CheckIn.employeeId"
            required
          >
            <option value="" disabled>Select Employee</option>
            <option
              v-for="user in users"
              :key="user.userId"
              :value="user.userId"
            >
              {{ user.firstName }} {{ user.lastName }}
            </option>
          </select></span
        >

        <span class="label">
          <div>Check In Date</div>
          <div>{{ currentDate }}</div>
        </span>

        <span class="label">
          <div>Performed By:</div>
          <div>
            {{ $store.state.user.firstName }} {{ $store.state.user.lastName }}
          </div>
        </span>

        <span class="label" for="notes">Check In Notes </span>

        <textarea
          id="notes"
          v-model="CheckIn.notes"
          required
          autofocus
        ></textarea>

        <button type="submit" id="submitCheckIn">Submit Check In</button>
        <message-modal :message="message" :type="type" v-if="isModalVisible" @close="closeModal" />
      </form>
    </div>
  </div>
</template>

<script>
import ProfessionalCheckInService from "../../services/ProfessionalCheckInService";
import MessageModal from '../../components/MODAL/MessageModal.vue';
export default {

components: {
  MessageModal
},


  computed: {
    currentUserId() {
      return this.$store.state.user.userId;
    },
  },
  data() {
    return {
      message: "created",
      type: "PROFESSIONAL CHECK IN",
      isModalVisible: false,
      CheckIn: {
        managerId: "",
        employeeId: "",
        notes: "",
        date: "",
      },
      currentDate: new Date().toISOString().slice(0, 10),
      users: [],
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
      this.$router.push({ name: "home" });

    },
    fetchUsers() {
      ProfessionalCheckInService.getUserInformation()
        .then((response) => {
          this.users = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submitCheckIn() {
      this.CheckIn.managerId = this.currentUserId;
      this.CheckIn.date = this.currentDate;

      ProfessionalCheckInService.addNewProfessionalCheckInByCheckIn(
        this.CheckIn
      ).then((response) => {
        this.CheckIn.notes = "";
        if (response.status === 201) {
          this.showModal();
        }
      });
    },
  },
};
</script>

<style scoped>
.label {
  display: flex;
  justify-content: space-between;
  width: 100%;
  font-size: 0.8em;
  margin-bottom: 10px;
}

.notes {
  border: 1px solid black;
  padding: 10px;
  text-align: left;
  font-size: 0.9em;
  margin-top: 10px;
  width: 100%;
  box-sizing: border-box;
}

.document-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

h4 {
  font-size: 1.2em;
  margin-bottom: 15px;
}
#user-select {
  max-width: 100%;
}
textarea {
  resize: none;
  box-sizing: border-box;
  width: 100%;
  height: 6em;
  margin-bottom: 10px;
}
#submitCheckIn {
  height: 4em;
  margin-top: 10px;
}
input {
  margin-bottom: 10px;
  width: 100%;
  box-sizing: border-box;
  height: 4em;
}
</style>