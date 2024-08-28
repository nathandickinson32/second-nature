<template>
  <div class="content">
    <h1>Create A Ticket</h1>
    <CreateMaintenanceTicket/>
  </div>
</template>

<script>
import CreateMaintenanceTicket from '../../components/MAINTENANCE/CreateMaintenanceTicket.vue';

export default {
    components: {
        CreateMaintenanceTicket
    }
}
</script>

<style scoped>
h1 {
    text-align: center;
}

.content {
    flex-direction: column;
    align-items: center;
}
</style>