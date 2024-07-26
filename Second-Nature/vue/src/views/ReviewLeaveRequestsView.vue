<template>
    <div class="container">
        <div v-for="request in requests" v-bind:key="request.id" class="request-card" >
            <form @submit.prevent="submitRequest">
                <div class="requestCard" :class="{ 'approved': request.status == 'Approved', 'denied': request.status == 'Denied' }">
                    <p>Requested by: {{ request.userName }}</p>
                    <p>Date Requested: {{ request.requestDate }}</p>
                    <p>Start Date: {{ request.startDate }}</p>
                    <p>End Date: {{ request.endDate }}</p>
                    <p>Reason: {{ request.requestReason }}</p>
                    <p>Status: {{ request.status }}</p>
                    <p v-if="request.status != 'Pending'">Manager comment: {{ request.comment }}</p>
                    <p v-if="request.status == 'Pending'" class="manager-comment">
                        <label for="comment">Manager Comment</label>
                        <input type="comment" id="comment" v-model="request.comment" autofocus/>
                    </p>
                    <p v-if="request.status != 'Pending'">Reviewed by manager: {{ request.reviewDate }}</p>
                    <span v-if="request.status == 'Pending'" class="action-buttons">
                        <button class="approve-button" @click="approveRequest(request)">Approve</button>
                        <button class="deny-button" :class="{'inactive_button' : !request.comment}" :disabled="!request.comment" @click="denyRequest(request)">Deny</button>
                    </span>
                </div>  
            </form>
        </div>
    </div>
</template>

<script>

import LeaveRequestService from '../services/LeaveRequestService';

export default {
    components: {
    },
    data() {
        return {
            requests: [],
            currentDate: new Date().toISOString().slice(0, 10)
        }
    },
    created() {
        this.fetchRequests();
    },
    computed(){
    },
    methods: {
        fetchRequests() {
            LeaveRequestService.getAllTimeOffRequests()
            .then((response) => {
                console.log(response.data);
                this.requests = response.data;
            this.requests.forEach(request => {
                console.log(request.userId);
                if(request.userId){
                    this.getUserName(request);
                }
            })
        })
        },
        approveRequest(request){
            request.reviewDate = this.currentDate;
            request.status = 'Approved'
            LeaveRequestService.updateTimeOffRequest(request)
            .then((response) => {
                console.log(response);
                if (response.status === 202) {
                    window.alert('Request has been approved');
                }
                this.fetchRequests();
            })
        },
        denyRequest(request){
            request.reviewDate = this.currentDate;
            request.status = 'Denied'
            LeaveRequestService.updateTimeOffRequest(request)
            .then((response) => {
                console.log(response);
                if (response.status === 202) {
                    window.alert('Request has been denied');
                }
                this.fetchRequests();
            })
            
        },
        getUserName(request) {
            LeaveRequestService.getUserById(request.userId)
            .then((response) => {
                console.log(response.data);
                if(response.status == 200){
                    request.userName = response.data.firstName + ' ' + response.data.lastName;
                }
            })
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
    border: 1px solid #a1af9f;
    border-radius: 5px;
    padding: 10px;
    padding-left: 20px;
    margin-top:10px;
    margin-bottom: 10px;
    width: 300px;
    background-color: white;
    box-shadow: -2px 2px 4px #a1af9f;
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
    background-color: rgba(255, 0, 0, 0.75);
    color: white;
    border-radius: 5px;
}

.approve-button {
    background-color: rgba(0, 128, 0, 0.75);
    color: white;
    border-radius: 5px;
}

.approved {
    background-color: rgba(163, 196, 163, 0.463);
}

.denied {
    background-color: darksalmon;
}

.inactive_button {
    background-color: rgba(109, 97, 97, 0.25);
}

</style>