<template>
  <div class="content">
    <h1>Add Equipment</h1>
    <CreateEquipment />
  </div>
</template>

<script>
import CreateEquipment from '../components/CreateEquipment.vue';
export default {
    components: {
        CreateEquipment
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