<template>
    <div class="content">
        <h1>Leave Requests</h1>
        <router-link v-bind:to="{ name: 'newPTOrequest'}" id="create-request-btn">Create a Request</router-link>
        <router-link v-if="isManager" v-bind:to="{ name: 'reviewLeaveRequests'}" id="create-request-btn">Review Employee Requests</router-link>

        <div v-if="requests.length===0">
          <h3>No Recent Requests</h3>
        </div>
        <div class="small-container" v-for="request in requests" v-bind:key="request.id"
            :class="{ 'approved': request.status == 'Approved', 'denied': request.status == 'Denied' }">
            <h4>{{ request.status }}</h4>
            <span class="label">Reason for Request: {{ request.requestReason }}</span>
            <span class="label">Date Range: {{ formatDate(request.startDate) }} - {{ formatDate(request.endDate) }}</span>
            <span class="label">Requested: {{ formatDate(request.requestDate) }}</span>
            
            <div class="review" v-if="request.status != 'Pending'">
                <span class="label">Reviewed on: {{ request.reviewDate }}</span>
                <span class="label" v-if="request.comment != ''">Comment: {{ request.comment }}</span>
            </div>
        </div>
       </div>
</template>

<script>
import LeaveRequestService from '../../services/LeaveRequestService';

export default {
    data() {
        return {
            requests: []
        }
    },
    created() {
        LeaveRequestService.getAllTimeOffRequestsByUserId()
            .then((response) => {
                this.requests = response.data;
                this.requests.forEach((request) => {
                    if (request.reviewDate == null) {
                        request.status = "Pending";
                    }
                });
            });
    },
    methods: {
        formatDate(dateTime) {
      const date = new Date(dateTime);

      const formattedDate = date.toLocaleDateString("en-us", {
        month: "short",
        day: "2-digit",
        year: "numeric",
      });

      return `${formattedDate}`;
    },
    },
    computed: {
    isManager() {
      return this.$store.getters.isManager;
    },
  },

}

</script>

<style scoped>
.content {
    flex-direction: column;
    align-items: center;
    gap: 20px;
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
#create-request-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #2a64bd ;
  border-radius: 10px;
  background-color: white;
  width: 90%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  color: #2a64bd;
}
</style>