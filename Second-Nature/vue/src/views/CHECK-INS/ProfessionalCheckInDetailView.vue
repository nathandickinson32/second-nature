<template>
    <div>
        <ProfessionalCheckInDetails :professionalCheckIn="professionalCheckIn" :managerName="managerName" :employeeName="employeeName"></ProfessionalCheckInDetails>

    </div>
</template>

<script>
import ProfessionalCheckInService from "../../services/ProfessionalCheckInService";
import LeaveRequestService from "../../services/LeaveRequestService";
import ProfessionalCheckInDetails from "../../components/CHECK-INS/ProfessionalCheckInDetails.vue";
export default {
  components: {
   ProfessionalCheckInDetails
  },
  data() {
    return {
      professionalCheckIn: {
        checkInId: "",
        managerId: "",
        employeeId: "",
        notes: "",
        date: "",
      },
      managerName: "",
      employeeName: "",
    };
  },
  created() {
    this.getProfessionalCheckIn();
  },
  methods: {
    getProfessionalCheckIn() {
      ProfessionalCheckInService.getProfessionalCheckInByCheckInId(this.$route.params.checkInId)
        .then((response) => {
          this.professionalCheckIn = response.data
          ProfessionalCheckInService.getUserById(this.professionalCheckIn.managerId)
            .then(
              (response1) => {
                this.managerName = response1.data.firstName + ' ' + response1.data.lastName;
              }
            ).catch((error) => {
              console.log(error.response);
            });
            ProfessionalCheckInService.getUserById(this.professionalCheckIn.employeeId)
            .then(
              (response2) => {
                this.employeeName = response2.data.firstName + ' ' + response2.data.lastName;
              }
            ).catch((error) => {
              console.log(error.response);
            });
        }
        ).catch((error) => {
          console.log(error.response);
        });
    }
  }
};
</script>

<style scoped>
</style>