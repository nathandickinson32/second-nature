<template>
  <div class="content">
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
            <label for="reason">Reason </label>
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
    </form>
  </div>
</template>

<script>
import LeaveRequestService from '../services/LeaveRequestService';

export default {
    data() {
        return {
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
        submitRequest() {
            this.request.requestDate = this.currentDate;
            if (this.request.requestReason === 'other') {
                this.request.requestReason = this.otherReason;
            }
            LeaveRequestService.addNewTimeOffRequest(this.request)
            .then((response) => {
                console.log(response);
                if (response.status === 201) {
                    window.alert('Request submitted successfully!');
                    this.$router.push({ name: 'home' });
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

form {
    display: flex;
    flex-direction: column;
    background-color: white;
    border-radius: 10px;
    border: 1px solid #a1af9f;
    padding: 20px;
    align-items: center;
    width: 90%;
    max-width: 400px;
    box-shadow: -2px 2px 4px #a1af9f;
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

.content {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px 5%;
}
</style>