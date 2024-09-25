<template>
  <div class="content">
    <div class="filter-section">
      <div class="filter-buttons">
        <label @click="showAllEquipment" class="clickable-label">Show All</label>
        <span class="separator"> | </span>
        <label @click="showActiveEquipment" class="clickable-label">Active</label>
        <span class="separator"> | </span>
        <label @click="showInactiveEquipment" class="clickable-label">Inactive</label>
        <span class="separator" v-if="isManager"> | </span>
        <label v-if="isManager" @click="showArchivedEquipment" class="clickable-label">Archived</label>
      </div>
      <!-- Dropdown list of equippment types -->
      <div class="form-input-group">
        <label for="equipmentType">Type of Equipment: </label>
        <select v-model="typeFilter" id="equipmentType" required>
          <option value="0">Any Equipment Type</option>
          <option v-for="type in types" :key="type.typeId" :value="type.typeId">
            {{ type.name }}
          </option>
        </select>
      </div>
    </div>
    <Equipment class="equipmentCard" v-for="equipment in filteredByType" v-bind:key="equipment.id" :equipment="equipment"></Equipment>
  </div>
</template>

<script>
import Equipment from './Equipment.vue';
import EquipmentService from '../../services/EquipmentService';
import TypeService from '../../services/TypeService';

export default {
  components: {
    Equipment,
  },
  data() {
    return {
      equipmentList: [],
      filterType: 'all',
      types: [],
      typeFilter: 0,
      filteredByTypeArray: [],
    };
  },
  created() {
    this.getEquipment();
    this.getTypes();
  },
  computed: {
    filteredEquipment() {
      console.log('filterType: ' + this.filterType);
      switch (this.filterType) {
        case 'active':
          return this.equipmentList.filter((equipment) => equipment.active === true && equipment.archived === false);
        case 'inactive':
          return this.equipmentList.filter((equipment) => equipment.active === false && equipment.archived === false);
        case 'archived':
          return this.equipmentList.filter((equipment) => equipment.archived === true);
        case 'all':
        default:
          return this.equipmentList.filter((equipment) => equipment.archived === false);
      }
    },
    isManager() {
      return this.$store.getters.isManager;
    },
    filteredByType() {
      console.log('typeFilter: ' + this.typeFilter);
      if (this.typeFilter == '0') {
        return this.filteredEquipment;
      }
      return this.filteredEquipment.filter((equipment) => equipment.typeId === this.typeFilter);
    },
  },
  methods: {
    getEquipment() {
      EquipmentService.getAllEquipment().then((response) => {
        this.equipmentList = response.data;
      });
    },
    getTypes() {
      TypeService.getAllTypes().then((response) => {
        this.types = response.data;
      });
    },
    showAllEquipment() {
      this.filterType = 'all';
    },
    showActiveEquipment() {
      this.filterType = 'active';
    },
    showInactiveEquipment() {
      this.filterType = 'inactive';
    },
    showArchivedEquipment() {
      this.filterType = 'archived';
    },
  },
};
</script>

<style scoped>
.content {
  box-sizing: border-box;
  width: 100%;
  flex-direction: column;
}

.filter-buttons {
    display: flex;
    justify-content: center;
    gap: 5px;
}
</style>
