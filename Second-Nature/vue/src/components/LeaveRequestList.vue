<template>
  <div>
    <h1>Leave Requests</h1>
    <div v-for="request in requests" v-bind:key="request.id">
        
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
        })
    }
}

</script>

<style>

</style>