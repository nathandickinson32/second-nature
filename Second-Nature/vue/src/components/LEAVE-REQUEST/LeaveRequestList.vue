<template>
    <div class="content">
        <h2>Leave Requests</h2>
        <div class="small-container" v-for="request in requests" v-bind:key="request.id"
            :class="{ 'approved': request.status == 'Approved', 'denied': request.status == 'Denied' }">
            <h5>Request</h5><h3>{{ request.requestReason }}</h3>
            <h5>Date Range</h5><p>{{ request.startDate }} - {{ request.endDate }}</p>
            <h5>Requested</h5><p>{{ request.requestDate }}</p>
            <h5>Status</h5><p>{{ request.status }}</p>
            <div class="review" v-if="request.status != 'Pending'">
                <h5>Reviewed on</h5><p>{{ request.reviewDate }}</p>
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
</style>