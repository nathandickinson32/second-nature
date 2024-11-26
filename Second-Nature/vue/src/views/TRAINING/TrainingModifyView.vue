<template>
  <div class="content"> 
    <div class="document-container">
      <h4>Modify Training Document</h4>
      <TrainingModifyList />
    </div>
  </div>
  <FooterVue/>
</template>

<script>
import FooterVue from '../../components/Footer.vue';
import TrainingModifyList from '../../components/TRAINING/TrainingModifyList.vue';
export default {
  components: {
    FooterVue,
    TrainingModifyList
  }

    // Update Training Resource DTO
    // {
    // 	"training_resource_id" : Intiger,
    // 	"title" : String
    // 	"category" : String (options include: "general", "policies", "winter", "spring", "summer", etc...),
    // 	"content" : String,
    // 	"resource_source" : String (upload location),
    // }
    // // Updated on date, and user id is handled on the backend Update controller.
    
    // Archive Training Resource DTO
    // {
    // 	"training_resource_id" : Integery / Number,
    // 	"archived_notes" : String (Required once the archived option is selected)
    // }
    // // updatedOnDate and updatedByUserId are handled on the backend by the principal.
    // // isArchived is updated on the backend and set to TRUE.
}
</script>


<style>

</style>