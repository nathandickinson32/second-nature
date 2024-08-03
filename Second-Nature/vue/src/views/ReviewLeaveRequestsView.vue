<template>
    <div class="content">
        <div v-for="request in requests" v-bind:key="request.id" class="small-container"
            :class="{ 'approved': request.status == 'Approved', 'denied': request.status == 'Denied' }">
            <form @submit.prevent="submitRequest">
                <h5>Request</h5>
                <p>{{ request.requestReason }}</p>
                <h5>Requested by</h5>
                <p>{{ request.userName }}</p>
                <h5>Date Range</h5>
                <p>From {{ request.startDate }} to {{ request.endDate }}</p>
                <h5>Requested on</h5>
                <p>{{ request.requestDate }}</p>
                <div class="review-container">
                    <h5>Approval Status</h5>
                    <p>{{ request.status }}</p>
                    <h5 v-if="request.comment != ''">Comment</h5>
                    <p v-if="request.status != 'Pending'">{{ request.comment }}</p>
                    <div v-if="request.status == 'Pending'" class="manager-comment">
                        <h5>Comment</h5>
                        <input type="comment" id="comment" v-model="request.comment" autofocus />
                    </div>
                    <div v-if="request.status != 'Pending'">
                        <h5>Reviewed on</h5>
                        <p>{{ request.reviewDate }}</p>
                    </div>
                    <span v-if="request.status == 'Pending'" class="approval-button-container">
                        <button class="approval-button approve-button" @click="approveRequest(request)">Approve</button>
                        <button class="approval-button deny-button" :class="{ 'inactive_button': !request.comment }"
                            :disabled="!request.comment" @click="denyRequest(request)">Deny</button>
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
    computed() {
    },
    methods: {
        fetchRequests() {
            LeaveRequestService.getAllTimeOffRequests()
                .then((response) => {
                    console.log(response.data);
                    this.requests = response.data;
                    this.requests.forEach(request => {
                        console.log(request.userId);
                        if (request.userId) {
                            this.getUserName(request);
                        }
                    })
                })
        },
        approveRequest(request) {
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
        denyRequest(request) {
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
                    if (response.status == 200) {
                        request.userName = response.data.firstName + ' ' + response.data.lastName;
                    }
                });
        }
    }
}

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
</style>