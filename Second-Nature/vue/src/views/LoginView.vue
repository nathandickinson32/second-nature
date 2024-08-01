<template>
  <div class="content">
    <div id="login">
      <h1>Please Sign In</h1>
      <form v-on:submit.prevent="login">
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <button type="submit">Sign in</button>
        <p>
          <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
button {
  width: 200px;
  height: 50px;
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

label {
  margin-right: 0.5rem;
}

#login {
  margin: 20px;
  background-color: white;
  border-radius: 10px;
  border-style: solid;
  border-width: 1px;
  width: auto;
  max-width: 400px;
  padding: 20px;
}

.content {
  display: flex;
  justify-content: center;
}

.form-input-group {
  margin: 1em 0;
  display: flex;
  justify-content: space-between;
  width: 100%;
}
</style>