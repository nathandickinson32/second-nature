<template>
    <div class="content">
        <h1>Leave Requests</h1>
        <router-link v-bind:to="{ name: 'newPTOrequest'}" id="create-request-btn">Create a Request</router-link>
        <router-link v-bind:to="{ name: 'reviewLeaveRequests'}" id="create-request-btn">Review Employee Requests</router-link>

        <div class="small-container" v-for="request in requests" v-bind:key="request.id"
            :class="{ 'approved': request.status == 'Approved', 'denied': request.status == 'Denied' }">
            <h5>Reason for Request:</h5><p>{{ request.requestReason }}</p>
            <h5>Date Range:</h5><p>{{ formatDate(request.startDate) }} - {{ formatDate(request.endDate) }}</p>
            <h5>Requested:</h5><p>{{ formatDate(request.requestDate) }}</p>
            <h5>Status:</h5><p>{{ request.status }}</p>
            <div class="review" v-if="request.status != 'Pending'">
                <h5>Reviewed on:</h5><p>{{ request.reviewDate }}</p>
                <span v-if="request.comment != ''"><h5>Comment</h5><p> {{ request.comment }}</p></span>
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
    }

}

</script>

<style scoped>
h2 {
    text-align: center;
    margin: 0;
}

h3 {
    margin: 0;
}

h5 {
    margin: 10px 0;
}

.content {
    flex-direction: column;
    align-items: center;
    gap: 20px;
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