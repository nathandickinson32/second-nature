<template>
  <div>New PTO Request form</div>
</template>

<script>
export default {
    data() {
        return {
            request: {
                startDate: null,
                endDate: null,
                reason: null
            }
        }
    },
    methods: {
        submitRequest() {
            console.log(this.request);
        }
    }
}
</script>

<style>

</style>