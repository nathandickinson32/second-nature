<template>
  <div class="content">
    <div class="document-container">
        <h4>Create a Maintenance Ticket</h4>
        <form v-on:submit.prevent="createMaintenanceTicket">
            <label for="equipment-identity">Select Equipment: </label>
            <select name="equipment-identity" id="equipment-identity" v-model="createMaintenanceTicketDto.equipmentId">
                <option value="" disabled>Select Equipment</option>
                <option v-for="equipmentIdentity in equipmentIdentityList" v-bind:key="equipmentIdentity.equipmentId" :value="equipmentIdentity.equipmentId">
                    {{ equipmentIdentity.name }} {{ equipmentIdentity.model }}
                </option>
            </select>

            <div v-if="selectedEquipment != null">
                <div v-show="createMaintenanceTicketDto.equipmentId != null" class="equipment-name-serial">
                    <span class="label">Name: {{ selectedEquipment.name }} </span> <br>
                    <span class="label">Model: {{ selectedEquipment.model }}</span> <br>
                    <span class="label">Serial Number: {{ selectedEquipment.serialNumber }}</span> 
                </div>

                <label for="hours">Current Hours</label>
                <input type="text" name="hours" id="hours" placeholder="Current hours" v-model="createMaintenanceTicketDto.hours">

                <label for="notes">Notes</label>
                <textarea name="notes" id="notes" v-model="createMaintenanceTicketDto.notes"></textarea>

                <div class="checkbox-div">
                    <input type="checkbox" name="complete" id="complete" v-model="createMaintenanceTicketDto.complete">
                    <label for="complete"> Complete</label>
                </div>

                <div class="maintenance-performed" v-for="(performed, index) in createMaintenanceTicketDto.createMaintenancePerformedDto" v-bind:key="index">
                    <h4>Maintenance Performed: </h4>
                    <input type="text" name="maintenance-performed-description" id="" placeholder="What is being done?" v-model="performed.description">
                    <input type="text" name="maintenance-performed-by" id="" placeholder="Who is doing the work?" v-model="performed.performedBy">
                    <textarea name="maintenance-performed-notes" id="maintenance-performed-notes" v-model="performed.notes"></textarea>
                </div>
                <button v-on:click.prevent="addMaintenancePerformed">Add another maintenance performed</button>
                <button v-on:click.prevent="subtractMaintenancePerformed">Remove last maintenance performed</button>
                <input type="submit" value="Create Ticket" id="submitTicket">
            </div>
            <message-modal :message="message" :type="type" v-if="isModalVisible" @close="closeModal" />

        </form>
    </div>
  </div>
</template>

<script>
import EquipmentService from '../../services/EquipmentService';
import MaintenanceService from '../../services/MaintenanceService';
import MessageModal from '../../components/MODAL/MessageModal.vue';

export default {
    components: {
  MessageModal
},
    data() {
        return {
            message: "Successfully Created",
            type: "MAINTENANCE",
            isModalVisible: false,
            createMaintenanceTicketDto : {
                equipmentId : null,
                createMaintenancePerformedDto : [
                    {
                        description : '',
                        performedBy : '',
                        notes : ''
                    }
                ]
            },
            equipmentIdentityList : [],
        }
    },
    mounted() {
        EquipmentService.getAllEquipmentIdentity().then((response) => {
            if (response.status == 200) {
                this.equipmentIdentityList = response.data;
            }
        }).catch((error) => {
            console.log("Error getting equipment identity list.")
        })
    },
    methods: {
        showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      
      this.isModalVisible = false;
      this.$router.push({ name: "maintenance-ticket-List" });

    },
        addMaintenancePerformed(){
            this.createMaintenanceTicketDto.createMaintenancePerformedDto.push(
                {
                    description : '',
                    performedBy : '',
                    notes : ''
                }
            );
        },
        subtractMaintenancePerformed(){
            this.createMaintenanceTicketDto.createMaintenancePerformedDto.pop();
        },
        createMaintenanceTicket(){
            this.createMaintenanceTicketDto.equipmentId = this.selectedEquipment.equipmentId;
            MaintenanceService.createMaintenanceTicket(this.createMaintenanceTicketDto).then((response) => {
                if (response.status == 201){
                  this.showModal();
                }
            }).catch((error) => {
                console.log("Error creating ticket.")
            })
        }
    },
    computed: {
        selectedEquipment() {            
            return this.equipmentIdentityList.find(equipmentIdentity => equipmentIdentity.equipmentId === this.createMaintenanceTicketDto.equipmentId);
        }
    }
}
</script>

<style scoped>
button {
    width: 100%;
    height: 4em;
    margin-top: 10px;
}

form {
    align-items: baseline;
    gap: 10px;
}

input {
    margin-bottom: 10px;
    width: 100%;
    box-sizing: border-box;
    height: 4em;
}

label {
    font-size: 0.8em;
}

textarea {
    resize: none;
    box-sizing: border-box;
    width: 100%;
    height: 6em;
    margin-bottom: 10px;
}

#submitTicket {
    height: 4em;
    margin-top: 10px;
}

#equipment-identity {
    max-width: 100%;
}

.checkbox-div {
    display: flex;
    justify-content: baseline;
    align-items: center;
    margin-bottom: 10px;
}

.checkbox-div input {
    width: 30px;
    height: 30px;
    margin-right: 10px;
}

.content {
    gap: 10px;
}

.label {
    font-size: 0.8em;
}


</style>