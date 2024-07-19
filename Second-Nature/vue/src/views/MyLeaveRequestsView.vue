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

export default {
    components: {
        LeaveRequestList
    }
}
</script>

<style>

</style>