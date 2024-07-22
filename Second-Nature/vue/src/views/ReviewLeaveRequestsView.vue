<template>
    <!-- <LeaveRequestList :request="requests"  /> -->
    <div class="container">
    <div v-for="request in requests" v-bind:key="request.id" class="request-card">
        <!-- <LeaveRequestList :request="request"  /> -->
         
            <div class="requestCard">
                <p>Date Requested: {{ request.requestDate }}</p>
                <p>Start Date: {{ request.startDate }}</p>
                <p>End Date: {{ request.endDate }}</p>
                <p>Reason: {{ request.requestReason }}</p>
                <p>Status: {{ request.status }}</p>
                <p v-if="request.status != 'Pending'">Manager comment: {{ request.comment }}</p>
                <p>Reviewed by manager: {{ request.reviewDate }}</p>
                <span class="action-buttons">
                    <button class="approve-button" @click="approveRequest(request)">Approve</button>
                    <button class="deny-button" @click="denyRequest(request)">Deny</button>
                </span>
            </div>    
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

<style scoped>

.container {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-around;
    gap: 30px;
}
.requestCard {
    display: flex;
    flex-direction: column;
    justify-content: start;
    border: 1px solid black;
    border-radius: 5px;
    padding: 10px;
    padding-left: 20px;
    margin-top:10px;
    margin-bottom: 10px;
    width: 300px;
}

p {
    margin: 2px;
    padding: 2px;
}

.action-buttons {
  display: flex;
  gap: 10px;
  padding-top: 3px;
  align-self: center;
}

.deny-button {
    background-color: red;
    color: white;
    border-radius: 5px;
}

.approve-button {
    background-color: green;
    color: white;
    border-radius: 5px;
}
</style>