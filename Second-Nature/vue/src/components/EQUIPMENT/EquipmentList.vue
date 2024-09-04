<template>
  <div class="content">
    <div class="filter-section">
            <label @click="showAllEquipment" class="clickable-label">Show All</label>
            <span class="separator"> | </span>
            <label @click="showActiveEquipment" class="clickable-label">Active</label>
            <span class="separator"> | </span>
            <label @click="showInactiveEquipment" class="clickable-label">Inactive</label>
            <span class="separator" v-if="isManager"> | </span>
            <!-- <label @click="showMowers" class="clickable-label">Mowers</label>
            <span class="separator"> | </span>
            <label @click="showBlowers" class="clickable-label">Blowers</label>
         -->
         <label v-if="isManager" @click="showArchivedEquipment" class="clickable-label">Archived</label>
           
        </div>
    <Equipment class="equipmentCard" v-for="equipment in filteredEquipment" v-bind:key="equipment.id" :equipment="equipment"></Equipment>
  </div>
</template>

<script>
import Equipment from './Equipment.vue';
import EquipmentService from '../../services/EquipmentService';

export default {
    components: {
        Equipment
    },
    data() {
        return {
            equipmentList: [],
            filterType: 'all'
        };
    },
    created() {
        this.getEquipment();
    },
    computed: {
        filteredEquipment() {
            if (this.filterType === 'all') {
                return this.equipmentList.filter(equipment => equipment.archived === false);
            }
            else if (this.filterType === 'active') {
                return this.equipmentList.filter(equipment => equipment.active === true);
            }
            else if (this.filterType === 'inactive') {
                return this.equipmentList.filter(equipment => equipment.active === false);
            }
            // else if (this.filterType === 'mowers') {
            //     return this.equipmentList.filter(equipment => equipment.active === false);
            // }
            // else if (this.filterType === 'blowers') {
            //     return this.equipmentList.filter(equipment => equipment.active === false);
            // }
            else if (this.filterType === 'archived') {
                return this.equipmentList.filter(equipment => equipment.archived === true);
            }
            return []; // Default return for unexpected filterType
        },
        isManager() {
      return this.$store.getters.isManager;
    }
    },
    methods: {
        getEquipment() {
            EquipmentService.getAllEquipment()
                .then((response) => {
                    this.equipmentList = response.data;
                })
        },
        showAllEquipment() {
            this.filterType = 'all';
        },
        showActiveEquipment(){
            this.filterType ='active'
        },
        showInactiveEquipment(){
            this.filterType ='inactive'
        },
        showMowers(){
            this.filterType= 'mowers'
        },
        showBlowers(){
            this.filterType= 'blowers'
        },
        showArchivedEquipment(){
            this,this.filterType= 'archived'
        }
    }

}
</script>

<style scoped>
.content {
    box-sizing: border-box;
    width: 100%;
    flex-direction: column;
}
</style>