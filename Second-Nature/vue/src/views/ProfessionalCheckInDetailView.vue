<!-- <template>
    <div>
        <ProfesionalCheckInDetail :professionalCheckIn="profesionalCheckIn" :managerName="managerName"></ProfesionalCheckInDetail>

    </div>
</template>

<script>
import ProfessionalCheckInService from "../services/ProfessionalCheckInService";
import LeaveRequestService from "../services/LeaveRequestService";
export default {
  components: {
    ProfesionalCheckInDetail,
  },
  data() {
    return {
      profesionalCheckIn: {
        checkInId: "",
        managerId: "",
        employeeId: "",
        notes: "",
        date: "",
      },
      managerName: "",
    };
  },
  created() {
    this.getProfessionalCheckIn();
  },
  methods: {
    getProfessionalCheckIn() {
      ProfessionalCheckInService.getProfessionalCheckInByCheckInId(
        this.$route.checkInId
      ).then((response) => {
        this.profesionalCheckIn = response.data;
        LeaveRequestService.getUserById(this.profesionalCheckIn.managerId)
          .then((response1) => {
            this.managerName =
              response1.data.firstName + " " + response1.data.lastName;
          })
          .catch((error) => {
            console.log(error.response);
          });
      });
    },
  },
};
</script>

<style scoped>
</style> -->