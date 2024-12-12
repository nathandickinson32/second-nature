<template>
  <CreateMaintenanceTicket/> 
  <FooterVue/>
</template>

<script>
import FooterVue from '../../components/Footer.vue';
import CreateMaintenanceTicket from '../../components/MAINTENANCE/CreateMaintenanceTicket.vue';

export default {
    components: {
        CreateMaintenanceTicket,
        FooterVue
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