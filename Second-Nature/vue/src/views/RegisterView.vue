<template>
    <div id="register" class="text-center">
      <h1>Create Account</h1>
      <form v-on:submit.prevent="register">
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="firstname">First Name</label>
          <input type="text" id="firstname" v-model="user.firstName" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="lastname">Last Name</label>
          <input type="text" id="lastname" v-model="user.lastName" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
        <div class="form-input-group">
          <label for="title">Title</label>
          <select id="title" v-model="user.title" required>
            <option value="Default Title">Default Title</option>
            <option value="manager">Manager</option>
          </select>
        </div>
        <button type="submit">Create Account</button>
        <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      </form>
    </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        confirmPassword: '',
        title: 'Default Title',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
button {
  width: 200px;
  height: 50px;
  margin: 20px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  width: auto;
}

h1 {
  width: auto;
  text-align: center;
  margin-top: 0;
}

select {
  width: 180px;
  height: 50px;
}

#register {
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: auto;
  background-color: white;
  border-radius: 10px;
  border-style: solid;
  border-width: 1px;
  border-color: #3e4a3d;
  margin: 20px 5%;
  max-width: 400px;
  padding: 20px;
}

.form-input-group {
  margin-bottom: 1rem;
  width: 100%;
  display: flex;
  justify-content: space-between;
}
</style>
