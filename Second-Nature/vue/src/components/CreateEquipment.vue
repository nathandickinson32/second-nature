<template>
    <div class="content">
        <div class="small-container">
            <form v-on:submit.prevent="createEquipment">
                <label for="serial-number">Serial Number: </label>
                <input type="text" name="serial-number" id="serial-number" placeholder="Serial Number" v-model="createEquipmentDto.serialNumber">

                <label for="model">Model: </label>
                <input type="text" name="model" id="model" placeholder="Model Number" v-model="createEquipmentDto.model">

                <label for="Name">Name: </label>
                <input type="text" name="name" id="name" placeholder="Equipment Name" v-model="createEquipmentDto.name">

                <label for="hours">Machine Hours: </label>
                <input type="text" name="hours" id="hours" placeholder="Hours on the machine" v-model="createEquipmentDto.startingHours">

                <label for="notes">Notes: </label>
                <textarea name="notes" id="notes" v-model="createEquipmentDto.notes"></textarea>

                <div class="checkbox-div">
                    <input type="checkbox" name="active" id="active" v-model="createEquipmentDto.active">
                    <label for="active">Equipment is active. </label>
                </div>

                <label for="active-notes">Activity Notes: </label>
                <textarea name="active-notes" id="active-notes" v-model="createEquipmentDto.activeNotes"></textarea>

                <div class="checkbox-div">
                    <input type="checkbox" name="archive" id="archive" v-model="createEquipmentDto.archived">
                    <label for="archive">Equipment is archived. </label>
                </div>  

                <input type="submit" value="Add Equipment">
            </form>
            {{ equipment }}
        </div>
    </div>
</template>

<script>
import EquipmentService from '../services/EquipmentService';

export default {
    data() {
        return {
            createEquipmentDto: {
                serialNumber: '',
                model: '',
                name: '',
                startingHours: 0,
                notes: '',
                active: true,
                activeNotes: '',
                archived: false
            },
            equipment: {}
        }
    },
    methods: {
        createEquipment(){
            console.log("Creating Equipment...")
            EquipmentService.createEquipment(this.createEquipmentDto).then((response) => {
                if (response.status == 201){
                    this.equipment = response.data;
                    window.alert("Equipment Added.");
                    this.$router.push({ name: 'equipmentList' });
                }
            }).catch((error) => {
                console.log("Error creating equipment.");
            })
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
    font-weight: normal;
    font-size: 0.8em;
}

textarea {
    resize: none;
    box-sizing: border-box;
    width: 100%;
    height: 6em;
    margin-bottom: 10px;
}

.content {
    box-sizing: border-box;
    width: 100%;
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

.small-container {
    width: 100%;
}
</style>