<template>
  <div class="content">
    <div id="login" class="small-container">
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
        <p>
          <router-link v-bind:to="{name: 'employee-time-stamp' }">Manage Your Time Stamps.</router-link>
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
    },

    timeStamp(){
      this.$router.push("time-stamp");
    }
    
  }
};
</script>

<style scoped>
button {
  width: 200px;
  height: 50px;
  margin: 20px;
}

h1 {
  text-align: center;
  margin-top: 0;
}

.content {
  margin-top: 10px;
}
</style>