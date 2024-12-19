<template>
  <CreateEquipment />
</template>

<script>
import CreateEquipment from '../../components/EQUIPMENT/CreateEquipment.vue';

export default {
    components: {
        CreateEquipment,
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
    gap: 10px;
}
</style>