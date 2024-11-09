<template>
  <div class="content">
    <h1>Training</h1>
    <TrainingDocumentList/>
  </div>
</template>

<script>
import TrainingDocumentList from '../../components/TRAINING/TrainingDocumentList.vue';

export default {
  
  components: {
    TrainingDocumentList
  },
  
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



</style>