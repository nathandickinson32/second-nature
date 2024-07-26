<template>
  <div>
    <h1>Leave Requests</h1>
    <div class="container">
        <div class="requestCard" v-for="request in requests" v-bind:key="request.id" :class="{ 'approved': request.status == 'Approved', 'denied': request.status == 'Denied' }">
            <p>Date Requested: {{ request.requestDate }}</p>
            <p>Start Date: {{ request.startDate }}</p>
            <p>End Date: {{ request.endDate }}</p>
            <p>Reason: {{ request.requestReason }}</p>
            <p>Status: {{ request.status }}</p>
            <p v-if="request.status != 'Pending'">Manager comment: {{ request.comment }}</p>
            <p>Reviewed by manager: {{ request.reviewDate }}</p>
        </div>
    </div>
  </div>
</template>

<script>
import LeaveRequestService from '../services/LeaveRequestService';

export default {
    data() {
        return {
            requests: []
        }
    },
    created() {
        LeaveRequestService.getAllTimeOffRequestsByUserId()
        .then((response) => {
            console.log(response);
            this.requests = response.data;
            this.requests.forEach((request) => {
                if (request.reviewDate == null) {
                    request.status = "Pending";
                }
            });
        });
    }
}

</script>

<style scoped>
h1 {
    text-align: center;
}
.container {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-around;
    gap: 30px;
}
.requestCard {
    border: 1px solid #a1af9f;
    border-radius: 5px;
    padding: 10px;
    padding-left: 20px;
    margin-bottom: 15px;
    width: 300px;
    background-color: white;
    box-shadow: -2px 2px 4px #a1af9f;
}
.approved {
    background-color: rgba(163, 196, 163, 0.463);
}

.denied {
    background-color: darksalmon;
}

</style>