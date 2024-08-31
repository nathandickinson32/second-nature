<template>
  <div class="content">
    <div class="large-container">
        <form id="equipment-form" @submit.prevent="onSubmit">
            <div class="form-input-group">
                <label for="title">Serial Number: </label>
                <input type="text" v-model="updateEquipment.serialNumber" id="serialNumber" required />
            </div>
            <div class="form-input-group">
                <label for="title">Model: </label>
                <input type="text" v-model="updateEquipment.model" id="model" required />
            </div>
            <div class="form-input-group">
                <label for="title">Name: </label>
                <input type="text" v-model="updateEquipment.name" id="name" required />

            </div>
            <!-- <div class="form-input-group">
                <label for="title">Current Hours: </label>
                <input type="text" v-model="equipment.startingHours" id="startingHours" required />
            </div> -->
            <div class="form-input-group">
                <label for="title">Notes: </label>
                <input type="text" v-model="updateEquipment.notes" id="notes" />

            </div>
        </form>
    </div>
    <div class="button-section">
        <label @click="goBack" class="clickable-label">Back to Equipment Details</label>
        <span class="separator"> | </span>
        <label @click="saveEquipment" class="clickable-label">Save Equipment Changes</label>
    </div>
  </div>
</template>

<script>
import EquipmentService from '../../services/EquipmentService';

export default {
    props: {
        equipmentId: {
            type: Number,
            required: true
        },
      
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
                typeId: '',
                // hours: 0,
                notes: '',
                active: true,
                activeNotes: '',
                updatedByUserId: '',
                archived: false
            }
        }
    },
    mounted(){
        EquipmentService.getEquipmentById(this.$route.params.equipmentId).then((response) => {
            if (response.status == 200) {
                this.equipment = response.data;
                this.updateEquipment.equipmentId = this.equipment.equipmentId;
                this.updateEquipment.serialNumber = this.equipment.serialNumber;    
                this.updateEquipment.model = this.equipment.model;  
                this.updateEquipment.name = this.equipment.name;
                this.updateEquipment.typeId = this.equipment.typeId;
                // this.updateEquipment.hours = this.equipment.startingHours;
                this.updateEquipment.notes = this.equipment.notes;
                this.updateEquipment.active = this.equipment.active;
                this.updateEquipment.activeNotes = this.equipment.activeNotes;  
                this.updateEquipment.archived = this.equipment.archived;
            }
        }).catch((error) => {
            console.log(error);
        })
    },
    methods: {
        goBack(equipment) {
            this.router.push({ name: 'equipment-detail2', params: { equipmentId: this.updateEquipment.equipmentId } });
        },
        saveEquipment() {
            EquipmentService.updateEquipment(this.updateEquipment)
            .then(response => {
                console.log(response.data);
                alert('Equipment Modifications updated!');
                // this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'detail');
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