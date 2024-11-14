<template>
    <div class="content">
        <h1>Time Card Detail</h1>
            <!-- NEED GET USERNAME BY TIMECARDID -->
            <!-- clockOutTime shows up with a random date when 
          time card has only been punched once (i.e. no clock out)-->
        <div class="small-container">
            <h4>Minutes Worked: {{ timeCard.totalMinutesWorked }} minutes</h4>
            <h5>Clock-out: {{ formatDateTime(timeCard.clockInTime) }}</h5>
        
            <h5>Clock-out: {{ formatDateTime(timeCard.clockOutTime) }}</h5>
            <h5>{{timeCard.roundedDateTime}}</h5>
        </div>
        <div class="small-container" id="edit-time-container" v-if="isManager">
            <form id="edit-time-form" v-on:submit.prevent="updateSelectedTimeCard">
                <h3>Edit Time Card</h3>
                <div class="form-input-group">                
                    <label for="">Clock-in Time:</label>
                    <input type="datetime-local" id="clock-in-time" v-model="updatedTimeCard.dateTimeIn"/>
                    <label for="">Clock-out Time: </label>
                    <input type="datetime-local" id="clock-out-time" v-model="updatedTimeCard.dateTimeOut"/>
                    <div id="archive-section">
                        <label for="" id="">Archive?</label>
                        <input type="checkbox" id="archive-checkbox" v-model="updatedTimeCard.isArchived"/>
                    </div>
                        <label for="">Notes: </label>
                        <input type="text" id="archived-notes" v-model="updatedTimeCard.archivedNotes"/>

                    <button type="submit" id="submitEdits">Submit Changes</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import TimeCardService from '../../services/TimeCardService';
export default{
    data(){
        return{
            timeCard:{

            },
            updatedTimeCard:{
                timeCardId: 0,
                dateTimeIn: "",
                dateTimeOut: "",
                clockedIn: "",
                totalMinutesWorked: "",
                clockInTime: "",
                clockOutTime:"",
                updatedOnDate: "",
                isArchived: "",
                archivedNotes: ""
            }
        }
    },
    computed:{
        isManager(){
            return this.$store.getters.isManager;
        },
    },
    mounted(){
        TimeCardService.getTimeCardById(this.$route.params.timeCardId).then((response) =>{
            if(response.status == 200){
                this.timeCard = response.data;
                this.updatedTimeCard.timeCardId = response.data.timeCardId;
            }
        }).catch((error) =>{
            console.log(error);
        })
    },
    methods:{
        formatDateTime(dateTime){
            const date = new Date(dateTime);
            const formattedDate = date.toLocaleDateString('en-us', {
                month: '2-digit',
                day: '2-digit',
                year: 'numeric'
            });
            const formattedTime = date.toLocaleTimeString('en-us', {
                hour: '2-digit',
                minutes: '2-digit',
                hour12: true
            })

            return `${formattedDate} at ${formattedTime}`;
        },
            // Format to match yyyy-MM-dd HH:mm:ss.SS
        // formatDateToBackend(dateTime) {
        //     const date = new Date(dateTime);
        //     const formattedDate = date.getFullYear() +
        //         '-' + String(date.getMonth() + 1).padStart(2, '0') +
        //         '-' + String(date.getDate()).padStart(2, '0') +
        //         ' ' + String(date.getHours()).padStart(2, '0') +
        //         ':' + String(date.getMinutes()).padStart(2, '0') +
        //         ':' + String(date.getSeconds()).padStart(2, '0') +
        //         '.' + String(date.getMilliseconds()).padStart(2, '0').slice(0, 2); // Limit milliseconds to 2 digits
        //     return formattedDate;
        // },

        // **** getting null pointer exception
        updateSelectedTimeCard(){
            // this.updatedTimeCard.dateTimeIn = this.formatDateToBackend(this.updatedTimeCard.dateTimeIn);
            // this.updatedTimeCard.dateTimeOut = this.formatDateToBackend(this.updatedTimeCard.dateTimeOut);
            TimeCardService.updateTimeCard(this.updatedTimeCard)
            .then((response)=>{
                console.log(response.data);
                if(response.status === 200 || response.status === 204){
                    window.alert('Time Stamp Updated')
                    this.$router.push({name:'time-card-detail', params: {timeCardId: this.updatedTimeCard.timeCardId}})
                }
            })
        }
    },
}
</script>

<style scoped>
.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.form-input-group{
    display: flex;
    flex-direction: column;
}
#archive-section{
    display: flex;
    
}

#archive-checkbox{
    margin: 0;
    box-sizing: content-box;
    margin-left: 5px;
}

#submitEdits{
    margin-top: 10px;
}

</style>