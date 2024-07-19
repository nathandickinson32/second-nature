<template>
  <div>
    <h1>Leave Requests</h1>
    <div v-for="request in requests" v-bind:key="request.id">
        <p>{{ request.requestDate }}</p>
        <p>{{ request.startDate }}</p>
        <p>{{ request.endDate }}</p>
        <p>{{ request.requestReason }}</p>
        <p>{{ request.status }}</p>
        <p>{{ request.comment }}</p>
        <p>{{ request.reviewDate }}</p>
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
        });
    }
}

</script>

<style>

</style>