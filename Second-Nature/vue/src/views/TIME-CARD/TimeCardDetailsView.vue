<template>
    <div class="content">
        <h1>Time Card Detail</h1>
            <!-- NEED GET USERNAME BY TIMECARDID -->
            <!-- clockOutTime shows up with a random date when 
          time card has only been punched once (i.e. no clock out)-->
        <div class="small-container">
            <h4>Minutes Worked: {{ timeCard.totalMinutesWorked }} minutes</h4>
            <h5>Clock-in: {{ formatDateTime(timeCard.clockInTime) }}</h5>
        
            <h5>Clock-out: {{ formatDateTime(timeCard.clockOutTime) }}</h5>
            <h5>{{timeCard.roundedDateTime}}</h5>
        </div>
        <div class="small-container" id="edit-time-container" v-if="isManager">
            <form id="edit-time-form">
                <h3>Edit Time Card</h3>
                <div class="form-input-group">                
                    <label for="">Clock-in Time:</label>
                    <input type="" id=""/>
                    <label for="">Clock-out Time: </label>
                    <input type="" id=""/>
                    <div id="archive-section">
                        <label for="" id="">Archive?</label>
                        <input type="checkbox" id="archive-checkbox"/>
                    </div>
                        <label for="">Notes: </label>
                        <input type="" id=""/>

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