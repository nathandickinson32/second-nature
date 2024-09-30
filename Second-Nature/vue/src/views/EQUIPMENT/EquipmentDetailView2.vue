<template>
    <div class="content">
      <!-- {{ view }} -->
      <h1>Equipment Detail</h1>
      <equipment-detail-2 :equipment="equipment" ></equipment-detail-2>
    </div>
    <FooterVue/>
  </template>
  
  <script>
import EquipmentDetail2 from '../../components/EQUIPMENT/EquipmentDetail2.vue';
import FooterVue from '../../components/Footer.vue';
import EquipmentService from '../../services/EquipmentService';
  
export default {
    components: {
     EquipmentDetail2,
     FooterVue
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
          active: true,
          activeNotes: '',
          archived: false
        },
      }
     
    },
 
    mounted() {
      this.getEquipment();
    },
    methods: {
      getEquipment(){
        if(this.$route.params.equipmentId > 0){
          EquipmentService.getEquipmentById(this.$route.params.equipmentId)
            .then((response) => {
              this.equipment = response.data;
            })
        }
      },
     
    }
  }
  </script>
  
  <style scoped>
  h1 {
    text-align: center;
  }
  
  .content {
    flex-direction: column;
    align-items: center;
    gap: 10px;
  }
  </style>