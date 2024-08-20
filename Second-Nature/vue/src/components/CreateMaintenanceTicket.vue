<template>
  <div class="content">
    <div class="small-container">
        <form action="">
            <label for="equipment-identity">Select Equipment: </label>
            <select name="equipment-identity" id="equipment-identity" v-model.number="createMaintenanceTicket.equipmentId">
                <option v-for="equipmentIdentity in equipmentIdentityList" v-bind:key="equipmentIdentity.equipmentId">{{ equipmentIdentity.equipmentId }}</option>
            </select>
            <span class="label">Name: </span> {{ equipmentName }} <br>
            <span class="label">Serial Number: </span> {{ serialNumber }}

            <label for="hours">Current Hours</label>
            <input type="text" name="hours" id="hours" placeholder="Current hours" v-model="createMaintenanceTicket.hours">

            <label for="notes">Notes</label>
            <textarea name="notes" id="notes" v-model="createMaintenanceTicket.notes"></textarea>

            <div class="checkbox-div">
                <input type="checkbox" name="complete" id="complete" v-model="createMaintenanceTicket.complete">
                <label for="complete"> Complete</label>
            </div>

            <div class="maintenance-performed" v-for="number in maintenancePerformedCount" v-bind:key="number">
                <h4>Maintenance Performed: </h4>
                <input type="text" name="maintenance-performed-description" id="" placeholder="What is being done?" v-model="createMaintenanceTicket.createMaintenancePerformedDto.description">
                <input type="text" name="maintenance-performed-by" id="" placeholder="Who is doing the work?" v-model="createMaintenanceTicket.createMaintenancePerformedDto.performedBy">
                <textarea name="maintenance-performed-notes" id="maintenance-performed-notes" v-model="createMaintenanceTicket.createMaintenancePerformedDto.notes"></textarea>
                <button v-on:click.prevent="addMaintenancePerformed">+</button>
            </div>
            <button v-on:click.prevent="subtractMaintenancePerformed">Remove Last</button>
        </form>
    </div>
    Maintenance Ticket <br>
    {{ createMaintenanceTicket }}
    <br>
    Equipment Identity list <br>
    {{  equipmentIdentityList }}

  </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';

export default {
    data() {
        return {
            createMaintenanceTicket : {
                equipmentId : null,
                createMaintenancePerformedDto : []
            },
            maintenancePerformed : {
                equipmentId : -1,
                description : '',
                performedBy : '',
                notes : ''
            },
            equipmentIdentityList : [],
            maintenancePerformedCount : 1
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
        addMaintenancePerformed(){
            this.maintenancePerformedCount++;
        },
        subtractMaintenancePerformed(){
            this.maintenancePerformedCount--;
        },
    },
    computed: {
        equipmentName() {
            const equipmentIdentity = this.equipmentIdentityList.find(equipmentIdentity => equipmentIdentity.equipmentId === this.createMaintenanceTicket.equipmentId);
            return equipmentIdentity ? equipmentIdentity.name : "Make a selection";
        },
        serialNumber() {
            const equipmentIdentity =this.equipmentIdentityList.find(equipmentIdentity => equipmentIdentity.equipmentId === this.createMaintenanceTicket.equipmentId);
            return equipmentIdentity ? equipmentIdentity.serialNumber : "";
        }
    }
}
</script>

<style scoped>
form {
    align-items: baseline;
}

input {
    margin-bottom: 10px;
    width: 100%;
    box-sizing: border-box;
}

label {
    font-size: 0.8em;
}

select {
    margin-bottom: 10px;
}

textarea {
    resize: none;
    box-sizing: border-box;
    width: 100%;
    height: 6em;
    margin-bottom: 10px;
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
    margin-bottom: 10px;
}

.maintenance-performed {
    margin-bottom: 10px;
}
</style>