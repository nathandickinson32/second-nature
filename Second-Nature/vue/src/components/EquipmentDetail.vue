<template>
    <div class="content">
        <div class="large-container">
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
                <label @click="modify" class="clickable-label">Modify Equipment</label>
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
                active: '',
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
        modify() {
            this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'modify');
        },
        toggleActivity() {
            this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'activeStatus');

        },
        archive() {
            this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'archive');

        },
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
                this.updateEquipment.equipmentId = this.equipmentDetail.equipmentId;
                this.updateEquipment.serialNumber = this.equipmentDetail.serialNumber;  
                this.updateEquipment.model = this.equipmentDetail.model;
                this.updateEquipment.name = this.equipmentDetail.name;
                this.updateEquipment.notes = this.equipmentDetail.notes;
                this.updateEquipment.active = this.equipmentDetail.active;  
                this.updateEquipment.activeNotes = this.equipmentDetail.activeNotes;
                this.updateEquipment.updatedByUserId = this.equipmentDetail.enteredByUserId;
                this.updateEquipment.archived = this.equipmentDetail.archived;
                EquipmentService.updateEquipment(this.updateEquipment)
                    .then(response => {
                        console.log(response.data);
                        this.updateEquipment.equipmentId = 0;
                        this.updateEquipment.serialNumber = '';
                        this.updateEquipment.model = '';
                        this.updateEquipment.name = '';
                        this.updateEquipment.notes = '',
                        this.updateEquipment.active = true,
                        this.updateEquipment.activeNotes = '',
                        this.updateEquipment.updatedByUserId= '',
                        this.updateEquipment.archived = false
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

<style scoped>

.content {
    flex-direction: column;
}

.button-section {
    padding-top: 15px;
}


</style>