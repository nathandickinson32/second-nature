<template>
  <div class="content">
    <h4>Reveiw Employee Leave Requests</h4>
    <div
      v-for="request in requests"
      v-bind:key="request.id"
      class="small-container"
      :class="{
        approved: request.status == 'Approved',
        denied: request.status == 'Denied',
      }"
    >
      <h3 v-if="request.status == 'Pending'">Review</h3>
      <h3 v-if="request.status == 'Approved'">Approved</h3>
      <h3 v-if="request.status == 'Denied'">Denied</h3>
      <form @submit.prevent="submitRequest">
        <span class="label"
          >Reason for Request: {{ request.requestReason }}</span
        >
        <span class="label">Requested by: {{ request.userName }}</span>
        <span class="label"
          >Date Range: {{ formatDate(request.startDate) }} To:
          {{ formatDate(request.endDate) }}</span
        >
        <span class="label"
          >Date Requested on: {{ formatDate(request.requestDate) }}</span
        >

        <div class="review-container">
          <h5>Approval Status:</h5>
          {{ request.status }}
          <p v-if="request.status != 'Pending'">{{ request.comment }}</p>
          <div v-if="request.status == 'Pending'" class="manager-comment">
            <h5>Comments:</h5>
            <input
              type="comment"
              id="comment"
              v-model="request.comment"
              autofocus
            />
          </div>
          <div v-if="request.status != 'Pending'">
            <h5>Date Reviewed on:</h5>
            <p>{{ request.reviewDate }}</p>
          </div>
          <span
            v-if="request.status == 'Pending'"
            class="approval-button-container"
          >
            <button
              class="approval-button approve-button"
              @click="approveRequest(request)"
            >
              Approve
            </button>
            <button
              class="approval-button deny-button"
              :class="{ inactive_button: !request.comment }"
              :disabled="!request.comment"
              @click="denyRequest(request)"
            >
              Deny
            </button>
          </span>
        </div>
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
import LeaveRequestService from "../../services/LeaveRequestService";
import MessageModal from "../../components/MODAL/MessageModal.vue";

export default {
  components: {
    MessageModal,
  },
  data() {
    return {
      message: "",
      type: "LEAVE REQUEST",
      isModalVisible: false,
      requests: [],
      currentDate: new Date().toISOString().slice(0, 10),
    };
  },
  created() {
    this.fetchRequests();
  },
  computed() {},
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
      this.$router.push({ name: "myRequests" });
    },

    formatDate(dateTime) {
      const date = new Date(dateTime);

      const formattedDate = date.toLocaleDateString("en-us", {
        month: "short",
        day: "2-digit",
        year: "numeric",
      });

      return `${formattedDate}`;
    },

    fetchRequests() {
      LeaveRequestService.getAllTimeOffRequests().then((response) => {
        this.requests = response.data;
        this.requests.forEach((request) => {
          if (request.userId) {
            this.getUserName(request);
          }
        });
      });
    },
    approveRequest(request) {
      request.reviewDate = this.currentDate;
      request.status = "Approved";
      LeaveRequestService.updateTimeOffRequest(request).then((response) => {
        if (response.status === 202) {
            this.message = "Request Approved";
            this.showModal();
        }
        this.fetchRequests();
      });
    },
    denyRequest(request) {
      request.reviewDate = this.currentDate;
      request.status = "Denied";
      LeaveRequestService.updateTimeOffRequest(request).then((response) => {
        if (response.status === 202) {
            this.message = "Request Approved";
            this.showModal();
        }
        this.fetchRequests();
      });
    },
    getUserName(request) {
      LeaveRequestService.getUserById(request.userId).then((response) => {
        if (response.status == 200) {
          request.userName =
            response.data.firstName + " " + response.data.lastName;
        }
      });
    },
  },
};
</script>

<style scoped>
#comment {
  width: 100%;
  box-sizing: border-box;
}

.content {
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.approval-button-container {
  display: flex;
  gap: 10px;
  width: auto;
  justify-content: center;
}

.approval-button {
  color: white;
  border-radius: 5px;
  border-color: black;
  border-width: 1px;
  border-style: solid;
  width: 150px;
  height: 50px;
}

.approve-button {
  background-color: rgba(30, 155, 30, 0.75);
}

.deny-button {
  background-color: #ff4848;
}

.approved {
  background-color: #f4fbf3;
}

.denied {
  background-color: #fdd4be;
}

.inactive_button {
  background-color: rgba(109, 97, 97, 0.25);
}

.manager-comment {
  margin-bottom: 10px;
  width: auto;
}
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
</style>