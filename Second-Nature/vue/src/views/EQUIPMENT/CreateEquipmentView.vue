<template>
  <CreateEquipment />
  <FooterVue/>
</template>

<script>
import CreateEquipment from '../../components/EQUIPMENT/CreateEquipment.vue';
import FooterVue from '../../components/Footer.vue';

export default {
    components: {
        CreateEquipment,
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
    gap: 10px;
}
</style>