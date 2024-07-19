<template>
  <div>
    <p>
        View leave requests submitted by logged-in user
    </p>
    <leave-request-list></leave-request-list>
  </div>
</template>

<script>
import LeaveRequestList from '../components/LeaveRequestList.vue'
import LeaveRequestService from '../services/LeaveRequestService'

export default {
    components: {
        LeaveRequestList
    },
    created() {
        this.fetchRequests();
    },
    methods: {
        fetchRequests() {
            LeaveRequestService.getAllTimeOffRequestsByUserId()
            .then((response) => {
                this.requests = response.data;
        })
        }
    }
}
</script>

<style>

</style>