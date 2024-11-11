<template>
  <div class="content">
    <router-link id="add-new-training-button" v-if="isManager" v-bind:to="{ name: 'training-new' }">Add New Training Document</router-link>
    <!-- Once desired filters are established replace below element with label elements that trigger filter logic -->
    <p>Filter | Options | Go | Here</p>

    <TrainingDocumentList/>
  </div>
  <Footer/>
</template>

<script>
import Footer from '../../components/Footer.vue';
import TrainingDocumentList from '../../components/TRAINING/TrainingDocumentList.vue';


export default {
  
  components: {
    TrainingDocumentList,
    Footer
  },
  computed: {
    isManager() {
      return this.$store.getters.isManager;
    }
  }
  
};
</script>

<style scoped>
embed {
  width: 90%;
  height: 100vh;
}

.content{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#add-new-training-button {
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #2a64bd ;
  border-radius: 10px;
  background-color: white;
  width: 90%;
  max-width: 400px;
  height: 50px;
  text-align: center;
  color: #2a64bd;
  margin-bottom: 10px;
}

</style>