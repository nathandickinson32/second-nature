<template>
  <div class="content">
        <button @click="clearPdf">Back to List</button>
      <iframe
        :src="url"
        width="100%"
        height="600px"
        style="border: none;"
        title="PDF Viewer"
      ></iframe>
  </div>
</template>

<script>
export default {
    props: {
        url: String
    },
    data() {
        return {
        }
    },
    methods: {
        clearPdf(){
            this.$emit('clear')
        }
    }
}
</script>

<style scoped>
button{
    margin: 15pt;
}
</style>