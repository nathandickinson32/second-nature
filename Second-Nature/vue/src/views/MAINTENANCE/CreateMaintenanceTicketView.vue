<template>
  <CreateMaintenanceTicket/> 
</template>

<script>
import CreateMaintenanceTicket from '../../components/MAINTENANCE/CreateMaintenanceTicket.vue';

export default {
    components: {
        CreateMaintenanceTicket,
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