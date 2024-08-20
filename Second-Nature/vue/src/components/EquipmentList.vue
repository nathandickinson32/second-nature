<template>
  <div class="content">
    <Equipment class="equipmentCard" v-for="equipment in equipmentList" v-bind:key="equipment.id" :equipment="equipment"></Equipment>
  </div>
</template>

<script>
import Equipment from './Equipment.vue';
import EquipmentService from '../services/EquipmentService';

export default {
    components: {
        Equipment
    },
    data() {
        return {
            equipmentList: []
        };
    },
    created() {
        this.getEquipment();
    },
    methods: {
        getEquipment() {
            EquipmentService.getAllEquipment()
                .then((response) => {
                    this.equipmentList = response.data;
                })
        }
    }

}
</script>

<style scoped>
.content {
    box-sizing: border-box;
    width: 100%;
    flex-direction: column;
}
</style>