<template>
  <div>
    <form @submit.prevent="submitRequest">
        <h1>New Request Form</h1>
        <div class="form-input-group">
            <label for="startDate">Start Date </label>
            <input type="date" id="startDate" v-model="request.startDate" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="endDate">End Date </label>
            <input type="date" id="endDate" v-model="request.endDate" required autofocus />
        </div>
        <!-- <div class="form-input-group">
            <label for="reason">Reason</label>
            <input type="text" id="requestReason" v-model="request.requestReason" required autofocus />
        </div> -->
        <div class="form-input-group">
            <label for="reason">Reason</label>
            <select id="title" v-model="request.requestReason" required>
                <option value="vacation">Vacation</option>
                <option value="sick">Sick</option>
                <option value="">Other</option>
            </select>
            <div class="form-input-group" v-if="request.requestReason === ''">
                <label for="reason">Reason</label>
                <input type="text" id="reason" v-model="request.requestReason" required autofocus />
            </div>
        </div>
        <button type="submit">Submit Request</button>
        {{ request }}
        {{ currentDate }}
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
                status: '',
                comment: '',
                reviewDate: null
            },
            currentDate: new Date().toISOString().slice(0, 10)
        }
    },
    methods: {
        submitRequest() {
            this.request.requestDate = this.currentDate;
            LeaveRequestService.addNewTImeOffRequest(this.request)
            .then((response) => {
                console.log(response);
            })
        }
    }
}
</script>

<style>

</style>