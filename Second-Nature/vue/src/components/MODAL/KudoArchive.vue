<template>
  <div class="modal-backdrop">
    <div class="modal">
      <div class="modal-content">
        <button type="button" class="btn-close" @click="close">x</button>
        
        <header class="modal-header">
          <slot name="header">Archiving Kudos</slot>
        </header>

        <section class="modal-body">
          <slot name="body"></slot>
          <p class="prompt">Please list reason(s) for archiving kudo:</p>
          <span id="data-container">
            <label for="kudoNotes">Notes </label>
            <input id="kudoNotes" type="text" v-model="archivedNotes" placeholder="Enter notes here..."/>
          </span>
        </section>

        <footer class="modal-footer">
          <slot name="footer"></slot>
          <span id="btn-container">
            <button :class="archivedNotes.length > 3 ? 'btn-green' : 'btn-disabled'" :disabled="archivedNotes.length < 4" @click="updateNotes">Save</button>
            <button type="button" class="btn-green" @click="close">
              Cancel
            </button>
          </span>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Modal",
  data() {
    return {
      archivedNotes: '',
    };
  },
  methods: {
    updateNotes() {
      this.$emit("updateNotes", this.archivedNotes);
      this.$emit("close");
    },
    close() {
      this.$emit("close");
    },
  },
};
</script>

<style >
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
  height: 25rem;
  width: 25rem;
  margin: auto;
}

.modal-content {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.modal-header,
.modal-footer {
  padding: 20px;
  display: flex;
}

.modal-header {
  position: relative;
  top: 1rem;
  border-bottom: 1px solid #eeeeee;
  color: #4aae9b;
  justify-content: center;
  align-items: flex-start;
  width: 70%;
  margin: auto;
}

.modal-footer {
  position: relative;
  bottom: 1rem;
  border-top: 1px solid #eeeeee;
  justify-content: center;
  width: 70%;
  margin: auto;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.prompt {
  margin-top: 5rem;
  margin-bottom: 1rem;
}

#data-container {
  margin-bottom: 5rem;
  margin-top: 1rem;
}

.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  border: none;
  font-size: 20px;
  padding: 10px;
  cursor: pointer;
  font-weight: bold;
  color: #1f1c1c;
  background: #a74444e3;
  border-radius: 5px;
  border: solid 1px white;
  margin: 10px;
}

.btn-green {
  color: white;
  background: #4aae9b;
  border: 1px solid #4aae9b;
  border-radius: 2px;
  cursor: pointer;
  width: fit-content;
}

.btn-disabled {
  color: rgba(255, 255, 255, 0.822);
  background: #4aae9c54;
  border: 1px solid #4aae9b;
  border-radius: 2px;
  cursor: pointer;
  width: fit-content;
}

#btn-container {
  display: flex;
  justify-content: space-around;
  width: 75%;
  margin: auto;
}
</style>
