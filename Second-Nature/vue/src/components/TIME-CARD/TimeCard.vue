<template>
  <div>
<h1>{{ timeCard.totalMinutesWorked}}</h1>

  </div>
</template>

<script>

export default {
  props: {
    timeCard: {
      type: Object,
      required: true
    }
  },
   data() {
    return {

    }
   },
   created() {
   
   }

}
</script>

<style>

</style>