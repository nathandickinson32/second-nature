<template>
  <div class="content">   
    <div class="button-section">
        <span @click="goBack" class="clickable-label">Back to Details</span>
        <span class="separator"> | </span>
        <span @click="saveEquipment" class="clickable-label">Save Changes</span>
        <message-modal :message="message" :type="type" v-if="isModalVisible" @close="closeModal" />

    </div>
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
 
  </div>
</template>

<script>
import EquipmentService from '../../services/EquipmentService';
import MessageModal from '../../components/MODAL/MessageModal.vue';

export default {
    // props: {
    //     equipmentId: {
    //         type: Number,
    //         required: true
    //     },
      
    // },
    data() {
        return {
            message: "updated",
      type: "EQUIPMENT",
      isModalVisible: false,
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
    components: {
  MessageModal
},
    methods: {
        showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      
      this.isModalVisible = false;
      this.$router.push({  name: 'equipment-detail2', params: { equipmentId: this.updateEquipment.equipmentId } });

    },
        goBack() {
            this.$router.push({ name: 'equipment-detail2', params: { equipmentId: this.updateEquipment.equipmentId } });
        },
        saveEquipment() {
            EquipmentService.updateEquipment(this.updateEquipment)
            .then(response => {
                this.showModal();


            })
            .catch(error => {
                console.log(error);
            });
        }
    }
}
</script>

<style scoped>
.content {
    flex-direction: column;
}

.button-section {
    text-align: center;
    margin-top: 20px;
    margin-bottom: 20px;
}

</style>