<template>
  <div class="content">
    <div class="small-container">
      <form @submit.prevent="submitCheckIn">
      

        <div class="form-input-group">
          <label for="checkInDate" class="title">
            <span class="title-text">Professional Check In </span>
            <span class="currentDate">{{ currentDate }}</span>
          </label>
        </div>

        <div class="form-input-group">
          <label for="manager"
          class="title"
            >
            <span>
                Performed By:

            </span>
            <span class="managerName">

                {{ $store.state.user.firstName }}
                {{ $store.state.user.lastName }}

            </span>
           

          </label>
        </div>
        <div class="form-input-group">
          <label for="userSelect">Employee: </label>
          <select v-model="CheckIn.employeeId" id="userSelect" required>
            <option value="" disabled>Select Employee</option>
            <option
              v-for="user in users"
              :key="user.userId"
              :value="user.userId"
            >
              {{ user.firstName }} {{ user.lastName }}
            </option>
          </select>
        </div>
        
        <div class="form-input-group">
          <label for="notes">Check In Notes </label>
        
            <textarea
           
            id="notes"
            v-model="CheckIn.notes"
            required
            autofocus
          ></textarea>

         
        </div>
        <button type="submit" class="submit">Submit Check In</button>
      </form>
    </div>
  </div>
</template>

<script>
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";

export default {
  computed: {
    currentUserId() {
      return this.$store.state.user.userId;
    },
  },
  data() {
    return {
      CheckIn: {
        managerId: "",
        employeeId: "",
        notes: "",
        date: "",
      },
      currentDate: new Date().toISOString().slice(0, 10),
      users: [],
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    fetchUsers() {
      ProfessionalCheckInService.getUserInformation()
        .then((response) => {
          this.users = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submitCheckIn() {
      this.CheckIn.managerId = this.currentUserId;
      this.CheckIn.date = this.currentDate;

      ProfessionalCheckInService.addNewProfessionalCheckInByCheckIn(
        this.CheckIn
      ).then((response) => {
        console.log(response.data);
        this.CheckIn.notes = "";
        if (response.status === 201) {
          window.alert("Check In submitted successfully!");
          this.$router.push({ name: "home" });
        }
      });
    },
  },
};
</script>

<style scoped>
.form-input-group {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.currentDate {
  text-align: right;
}
.managerName{
    margin-left: auto;
    text-align: right;
}
#notes{
    border-radius: 5px;
}
#userSelect{
    border-radius: 5px;
    margin-left: auto;
}
.submit{
   align-self: flex-end;
}
.note-text{
display: flex;
width: 100%;

}
.form-input-group {
    display:block;
    

}



textarea {
display:block;
position: relative;
}
.manager{
    align-self: flex-start;
}

</style>