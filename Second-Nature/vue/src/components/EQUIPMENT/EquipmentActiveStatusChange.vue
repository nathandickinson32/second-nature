<template>
  <div class="content">
    <form id="equipment-activity-form" @submit.prevent="onSubmit">
      <div>
        <h3>Modify the Active Status for: {{ equipment.name }} ; {{ equipment.model }}</h3>
      </div>
        <div class="form-input-group">
          <label for="userSelect">Equipment is Active: </label>
          <select v-model="equipment.active" id="activeStatus" required>
              <option v-for="option in options" :key="option.Id" :value="option">
                  {{ option }}
              </option>
          </select>
        </div>

        <div class="form-input-group">
            <label for="title">Notes about Active Status: </label>
            <input type="text" v-model="equipmentActivity.activeNotes" id="activeNotes" required />

        </div>
        <div class="button-section">
                <label @click="goBack" class="clickable-label">Back to Equipment Details</label>
                <span class="separator"> | </span>
                <label @click="saveActiveStatus" class="clickable-label">Save Active Status Changes</label>
        </div>
    </form>
  </div>
</template>

<script>
import EquipmentService from '../../services/EquipmentService';    

export default {
  // props: {
  //   equipmentId: {
  //     type: Number,
  //     required: true
  //   }
  // },
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
        archived: ''
      },
      options: {
        true: 'true',
        false: 'false'
      }
    }
  },
  computed: {
    equipmentActivity() {
      return {
        equipmentId: this.equipment.equipmentId,
        active: this.equipment.active,
        activeNotes: this.equipment.activeNotes,
        updatedByUserId: ''
      };
    }
  },
  mounted() {
    this.getEquipment();
  },
  methods: {
    getEquipment() {
      EquipmentService.getEquipmentById(this.$route.params.equipmentId)
        .then((response) => {
          this.equipment = response.data;
          this.equipment.activeNotes = '';
        })
    },
    goBack() {
      this.$router.push({ name: 'equipment-detail2', params: { equipmentId: this.equipment.equipmentId } });
    },
    saveActiveStatus() {
      EquipmentService.updateEquipmentActivity(this.equipmentActivity)
      .then(response => {
         console.log(response.data);
         alert('Equipment activity updated!');
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