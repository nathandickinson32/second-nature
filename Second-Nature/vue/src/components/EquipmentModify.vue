<template>
  <div class="large-container">
    <form id="equipment-form" @submit.prevent="onSubmit">
        <div class="form-input-group">
            <label for="title">Serial Number: </label>
            <input type="text" v-model="equipment.serialNumber" id="serialNumber" required />
        </div>
        <div class="form-input-group">
            <label for="title">Model: </label>
            <input type="text" v-model="equipment.model" id="model" required />
        </div>
        <div class="form-input-group">
            <label for="title">Name: </label>
            <input type="text" v-model="equipment.name" id="name" required />

        </div>
        <div class="form-input-group">
            <label for="title">Current Hours: </label>
            <input type="text" v-model="equipment.startingHours" id="startingHours" required />

        </div>
        <!-- <div class="form-input-group">
            <label for="title">Currently Active: </label>
            <input type="text" v-model="equipment.active" id="active" required />

        </div>
        <div class="form-input-group">
            <label for="title">Notes about Active Status: </label>
            <input type="text" v-model="equipment.activeNotes" id="activeNotes" required />

        </div> -->
        <div class="form-input-group">
            <label for="title">Notes: </label>
            <input type="text" v-model="equipment.notes" id="notes" />

        </div>
        <div class="button-section">
                <label @click="goBack" class="clickable-label">Back to Equipment Details</label>
                <span class="separator"> | </span>
                <label @click="saveEquipment" class="clickable-label">Save Equipment Changes</label>
        </div>
    </form>
  </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';

export default {
    props: {
        equipmentId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            equipment: {
                equipmentId: -1,
                serialNumber: '',
                model: '',
                name: '',
                startingHours: '',
                enteredByUserId: '',
                notes: '',
                active: true,
                activeNotes: '',
                archived: false
            },
            updateEquipment: {
                equipmentId: 0,
                serialNumber: '',
                model: '',
                name: '',
                notes: '',
                active: true,
                activeNotes: '',
                updatedByUserId: '',
                archived: false
            }, 
            archivedEquipment: {
                equipmentId: 0,
                updatedByUserId: '',
                archived: true
            }
        }
    },
    mounted(){
        EquipmentService.getEquipmentById(this.$route.params.equipmentId).then((response) => {
            if (response.status == 200) {
                this.equipment = response.data;
            }
        }).catch((error) => {
            console.log(error);
        })
    },
    methods: {
        goBack() {
            this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'detail');
        },
        saveEquipment() {
            EquipmentService.updateEquipment(this.equipmentActivity)
            .then(response => {
                console.log(response.data);
                alert('Equipment Modifications updated!');
                this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'detail');
                this.$router.push({ name: 'equipmentList' });
            })
            .catch(error => {
                console.log(error);
            });
        }
    }
}
</script>

<style>

</style>