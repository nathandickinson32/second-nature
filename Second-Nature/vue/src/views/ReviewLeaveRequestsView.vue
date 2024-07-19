<template>
    <!-- <LeaveRequestList :request="requests"  /> -->
    <div v-for="request in requests" v-bind:key="request.id" class="request-card">
        <!-- <LeaveRequestList :request="request"  /> -->
         <div class="container">
            <div class="requestCard">
                <p>Date Requested: {{ request.requestDate }}</p>
                <p>Start Date: {{ request.startDate }}</p>
                <p>End Date: {{ request.endDate }}</p>
                <p>Reason: {{ request.requestReason }}</p>
                <p>Status: {{ request.status }}</p>
                <p v-if="request.status != 'Pending'">Manager comment: {{ request.comment }}</p>
                <p>Reviewed by manager: {{ request.reviewDate }}</p>
            </div>
        </div>
        <div class="action-buttons">
            <button class="approve-button" @click="approveRequest(request)">Approve</button>
            <button class="deny-button" @click="denyRequest(request)">Deny</button>
        </div>
    </div>
</template>

<script>

import LeaveRequestService from '../services/LeaveRequestService';
// import LeaveRequestList from '../components/LeaveRequestList.vue';

export default {
    components: {
        // LeaveRequestList
    },
    data() {
        return {
            requests: []
        }
    },
    created() {
        this.fetchRequests();
    },
    methods: {
        fetchRequests() {
            LeaveRequestService.getAllTimeOffRequests()
            .then((response) => {
                this.requests = response.data;
        })
        },
        approveRequest(request){
            // send the post with the leave request status === approve
        },
        denyRequest(request){
            // send the post with the leave request status === denied
        }
    }
}


</script>

<style>
.request-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}


.action-buttons {
  display: flex;
  gap: 10px;
}

.deny-button {
    background-color: red;
    color: white;
}

.approve-button {
    background-color: green;
    color: white;
}
</style>