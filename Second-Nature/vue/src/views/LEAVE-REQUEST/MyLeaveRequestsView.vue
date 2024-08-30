<template>
  <div>
    <leave-request-list></leave-request-list>
  </div>
</template>

<script>
import LeaveRequestList from '../../components/LEAVE-REQUEST/LeaveRequestList.vue'
import LeaveRequestService from '../../services/LeaveRequestService'

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