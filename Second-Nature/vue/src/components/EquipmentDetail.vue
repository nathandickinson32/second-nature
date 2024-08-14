<template>
  <div class="large-container">
    <form id="equipment-form" @submit.prevent="onSubmit">
        <div class="form-input-group">
            <label for="title">Serial Number: </label>
            <input type="text" v-model="equipmentDetail.serialNumber" id="serialNumber" required />
        </div>
        <div class="form-input-group">
            <label for="title">Model: </label>
            <input type="text" v-model="equipmentDetail.model" id="model" required />
        </div>
        <div class="form-input-group">
            <label for="title">Name: </label>
            <input type="text" v-model="equipmentDetail.name" id="name" required />

        </div>
        <div class="form-input-group">
            <label for="title">Current Hours: </label>
            <input type="text" v-model="equipmentDetail.startingHours" id="startingHours" required />

        </div>
        <div class="form-input-group">
            <label for="title">Currently Active: </label>
            <input type="text" v-model="equipmentDetail.active" id="active" required />

        </div>
        <div class="form-input-group">
            <label for="title">Notes about Active Status: </label>
            <input type="text" v-model="equipmentDetail.activeNotes" id="activeNotes" required />

        </div>
        <div class="form-input-group">
            <label for="title">Notes: </label>
            <input type="text" v-model="equipmentDetail.notes" id="notes" />

        </div>
    </form>
  </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';

export default {
    props: {
        equipment: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            equipmentDetail: {
                equipmentId: 0,
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
    created(){
        this.equipmentDetail = this.equipment;
    },
    methods: {
        onSubmit() {
            if(this.equipmentDetail.archive){
                this.archivedEquipment.equipmentId = this.equipmentDetail.equipmentId;
                EquipmentService.archiveEquipment(this.archivedEquipment)
                    .then(response => {
                        console.log(response.data);
                        alert('Equipment archived!');
                        this.$router.push({ name: 'equipment' });
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
            if (this.equipment.equipmentId < 1) {
                EquipmentService.addEquipment(this.equipmentDetail)
                    .then(response => {
                        console.log(response.data);
                        this.$router.push({ name: 'equipment-list' });
                    })
                    .catch(error => {
                        console.log(error);
                    });
            } else {
                EquipmentService.updateEquipment(this.equipmentDetail)
                    .then(response => {
                        console.log(response.data);
                        this.$router.push({ name: 'equipment-list' });
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        }
    }
}
</script>

<style>

</style>