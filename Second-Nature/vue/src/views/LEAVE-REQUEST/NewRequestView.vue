<template>
    <div class="content">
        <div class="small-container">
            <form @submit.prevent="submitRequest">
                <h2>New Request Form</h2>
                <div class="form-input-group">
                    <label for="startDate">Start Date </label>
                    <input type="date" id="startDate" v-model="request.startDate" required autofocus />
                </div>
                <div class="form-input-group">
                    <label for="endDate">End Date </label>
                    <input type="date" id="endDate" v-model="request.endDate" required autofocus />
                </div>
                <div class="form-input-group">
                    <label for="reason">Reason for Request </label>
                    <select id="title" v-model="request.requestReason" required>
                        <option value="vacation">Vacation</option>
                        <option value="sick">Sick</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                <div class="form-input-group" v-if="request.requestReason === 'other'">
                    <label for="reason">More Info </label>
                    <input type="text" id="reason" v-model="otherReason" required autofocus />
                </div>
                
                <button type="submit">Submit Request</button>
                <message-modal :message="message" :type="type" v-if="isModalVisible" @close="closeModal" />

            </form>
        </div>

    </div>
</template>

<script>
import LeaveRequestService from '../../services/LeaveRequestService';
import MessageModal from '../../components/MODAL/MessageModal.vue';

export default {
    components: {
  MessageModal
},
    data() {
        return {
            message: "Successfully Created",
            type: "LEAVE REQUEST",
            isModalVisible: false,
            request: {
                requestDate: null, //"2024-07-18" or "yyyy-mm-dd" format
                startDate: null,
                endDate: null,
                requestReason: '',
                status: 'Pending',
                comment: '',
                reviewDate: null
            },
            currentDate: new Date().toISOString().slice(0, 10),
            otherReason: ''
        }
    },
    methods: {
        showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      
      this.isModalVisible = false;
      this.$router.push({ name: "home" });

    },
        submitRequest() {
            this.request.requestDate = this.currentDate;
            if (this.request.requestReason === 'other') {
                this.request.requestReason = this.otherReason;
            }
            LeaveRequestService.addNewTimeOffRequest(this.request)
                .then((response) => {
                    console.log(response);
                    if (response.status === 201) {
                      this.showModal();
                    }
                })
        }
    }
}
</script>

<style scoped>
button {
    width: 200px;
    height: 50px;
}

h2 {
    text-align: center;
    margin-top: 0;
}

input {
    width: 150px;
}

select {
    width: 180px;
    height: 50px;
}
</style>