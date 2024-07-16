<template>
  <div class="home">
    <h1>Second Nature</h1>
    <p>Welcome, {{ this.$store.state.user.firstName }}!</p>
    <p>App Squares go here</p>
    <div id="menu-grid">
      <span>testing</span>
      <span>testing</span>
      <span>testing</span>
      <span>testing</span>
      <div>test</div>
      <div>test</div>
      <div>test</div>
      <div>test</div>
    </div>
  </div>
</template>

<script>
export default {
};
</script>

<style scoped>
#menu-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-gap: 10px;
}
</style>