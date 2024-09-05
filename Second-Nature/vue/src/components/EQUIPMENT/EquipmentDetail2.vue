<template>
  <div class="content">
    <div class="button-section">
      <router-link v-if="isManager" v-bind:to="{ name: 'equipment-modify' }">Modify</router-link>
      <span v-if="isManager" class="separator"> | </span>
      <!-- <label @click="toggleActivity" class="clickable-label">Change Active Status</label> -->
      <!-- <router-link v-bind:to="{ name: 'equipment-status' }">Status</router-link> -->

      <!-- <span class="separator"> | </span> -->
      <!-- <label @click="archive" class="clickable-label">Archive</label> -->
      <router-link
        v-bind:to="{ name: 'equipment-archive' }"
        v-if="isManager"
        class="button"
        >Archive</router-link
      >

      <span class="separator" v-if="isManager"> | </span>
      <router-link v-bind:to="{ name: 'equipmentList' }"
        >Back to Equipment List</router-link
      >
      
    </div>
    <div class="small-container">
      <form v-if="isManager" id="equipment-activity-form" @submit.prevent="onSubmit">

<span>
  <input
  v-model="statusEquipment.active"
    type="radio"
    id="activeBtn"
    :value="true"
    :checked="equipment.active == true"
    @change="toggleActivity"
    
  />
  <label for="activeBtn">Active</label>
  <input
  v-model="statusEquipment.active"

    type="radio"
    id="inactiveBtn"
    :value="false"
    :checked="equipment.active == false"
    @change="toggleActivity"
  />
  <label for="inactiveBtn">Inactive</label>
  <input type="text" v-model="statusEquipment.activeNotes" v-show="statusChange"/>
  <button v-show="statusChange" @click="saveActiveStatus">Submit</button>
</span>
</form>
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
        <span> {{ equipment.active ? 'Yes' : 'No'}}</span>
      </div>
      <div class="detail-display">
        <label for="label">Notes about Active Status: </label>
        <span> {{ equipment.activeNotes }}</span>
      </div>
      <div class="detail-display">
        <label for="label">Notes: </label>
        <span id="notes"> {{ equipment.notes }}</span>
      </div>
      

        <div v-for="maintenanceTicket in maintenanceTickets" v-bind:key="maintenanceTicket.maintenanceTicketId">
        <hr>
        <span class="label">Maintenance Performed</span> <br>
        <span class="label">Description: </span> {{ maintenanceTicket.description }} <br>
        <span class="label">Performed By: </span>{{ maintenanceTicket.performedBy }} <br>
        <span class="label">Notes: </span> <br>
        {{ maintenanceTicket.notes }}
      </div>

      <!-- <span>{{ maintenanceTicket.description }}</span> -->
     

    </div>
    
    
  </div>
</template>

<script>
import EquipmentService from "../../services/EquipmentService";

export default {
  data() {
    return {
        statusEquipment: {
          equipmentId: 0,
          active: true,
          activeNotes: '',
          updatedByUserId: '',
      },
      maintenanceTickets: [],

      statusChange: false,
    };
  },
  computed: {
   
    isManager() {
      return this.$store.getters.isManager;
    },
    
  },
  props: {
    equipment: {
      type: Object,
      required: true,
    },
  },
  mounted() {
    this.getEquipment();
    this.getEquipmentMaintenanceTickets();

  },
  methods: {
    getEquipment(){
      EquipmentService.getEquipmentById(this.$route.params.equipmentId)
        .then((response) => {
        //   this.archivedEquipment = response.data;
          this.statusEquipment.equipmentId = response.data.equipmentId;
          this.statusEquipment.active = response.data.active;
        })
    },
    toggleActivity() {
      // this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'activeStatus');
        // if(this.statusEquipment.active!=this.equipment.active){
        //     this.statusChange = true;

        // }else{
        //     this.statusChange= false;
        // }
        this.statusChange = this.statusEquipment.active !== this.equipment.active;

    },
    archive() {
      // this.$store.commit("SET_EQUIPMENT_DETAIL_VIEW", 'archive');
    },
    saveActiveStatus() {
      EquipmentService.updateEquipmentActivity(this.statusEquipment)
      .then(response => {
         console.log(response.data);
         alert('Equipment activity updated!');
         this.$router.push({ name: 'equipmentList' });
       })
       .catch(error => {
         console.log(error);
       });
    },
    getEquipmentMaintenanceTickets(){
       
          EquipmentService.getEquipmentMaintenanceById(this.$route.params.equipmentId)
            .then((response) => {
              console.log(response.data);
              this.maintenanceTickets = response.data;
            })
        
      }
  },
};
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