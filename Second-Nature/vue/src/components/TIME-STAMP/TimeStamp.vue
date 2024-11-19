<template>
  <h3>{{formatTime(timeCard.dateTimeIn )}} </h3>
  <h3>Clocked In</h3>
  <h3>{{formatTime(timeCard.dateTimeOut )}}</h3>
  <h3>Clocked Out</h3>

</template>

<script>
export default {
    created(){
           console.log(this.timeCard.dateTimeIn);
    },
props: {
    timeCard: {
        type: Object,
        required: true
    }
},
methods:{
      formatTime(dateTime){
        const date = new Date(dateTime);
        const formattedTime = date.toLocaleTimeString([], {
          hour: '2-digit',
          minute: '2-digit',
          hour12: true
        });

        return `${formattedTime}`;
      },
      formatDate(dateTime){
        const date = new Date(dateTime);

        const formattedDate = date.toLocaleDateString('en-us', {
          month: '2-digit',
          day: '2-digit',
          year: 'numeric'
        });

      
        
        return `${formattedDate}`;
      },

      checkDate(date){
        const today = new Date.now;
       if( this.formatDate(today) == this.formatDate(date)){
        return "Today";
       }


      }
    }
}
</script>

<style>

</style>