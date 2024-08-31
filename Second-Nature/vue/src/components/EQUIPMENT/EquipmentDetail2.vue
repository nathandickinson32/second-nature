<template>
    <div class="content">
        <div class="small-container">
                <div class="detail-display">
                    <label for="label">Serial Number: </label>
                    <span> {{ equipment.serialNumber }}</span> 
                </div>
                <div class="detail-display">
                    <label for="label">Model: </label>
                    <span> {{ equipment.model }}</span>
                </div>
                <div class="detail-display">
                    <label for="label">Name: </label>
                    <span> {{ equipment.name }}</span>
                </div>
                <div class="detail-display">
                    <label for="label">Current Hours: </label>
                    <span> {{ equipment.startingHours }}</span>
                </div>
                <div class="detail-display">
                    <label for="label">Currently Active: </label>
                    <span> {{ equipment.active }}</span>
                </div>
                <div class="detail-display">
                    <label for="label">Notes about Active Status: </label>
                    <span> {{ equipment.activeNotes }}</span>
                </div>
                <div class="detail-display">
                    <label for="label">Notes: </label>
                    <span id="notes"> {{ equipment.notes }}</span>
                </div>
        </div>
        <div class="button-section">
            <router-link v-bind:to="{ name: 'equipment-modify' }">Modify</router-link>
            <span class="separator"> | </span>
                <label @click="toggleActivity" class="clickable-label">Change Active Status</label>
                <span class="separator"> | </span>
                <label @click="archive" class="clickable-label">Archive</label>
                <span class="separator"> | </span>
                <router-link v-bind:to="{ name: 'equipmentList' }">Back to Equipment List</router-link>
        </div>
    </div>
</template>

<script>
import EquipmentService from '../../services/EquipmentService';

export default {
    props: {
        equipment: {
            type: Object,
            required: true
        }
    },
   
 
    methods: {
        modify(equipment) {
            this.router.push({ name: 'equipment-modify', params: { equipmentId: equipment.equipmentId } });
        },
        toggleActivity() {
            this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'activeStatus');

        },
        archive() {
            this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'archive');

        },
       
    }
}
</script>

<style scoped>
label {
    font-weight: normal;
    font-size: 0.8em;
}

.detail-display span {
    display: block;
    margin-bottom: 10px;
}

.content {
    width: 100%;
    box-sizing: border-box;
}
</style>