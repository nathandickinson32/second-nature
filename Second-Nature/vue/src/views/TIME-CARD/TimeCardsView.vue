<template>
    <div class="content">
        <h1>Time Cards</h1>
        <form id="filter-Form">
          <div>
            <label for="date-input-label">Select A Date: </label>
            <input type="date" id="date-select" v-model="dateFilter" required autofocus/>
          </div>
        </form>
        <!-- <router-link class="small-container" :to="{name: 'time-card-detail' , params: {timeCardId: timeCard.timeCardId}}"> -->
            <!-- <h3>Hour Type</h3> -->
            <!-- {{ timeCard.hour_type }} -->
            <!-- <h5>Date and Time</h5> -->
            <!-- timeCard.date_time -->
            <TimeCardList :timeCards="filteredTimeCards"/>
        <!-- </router-link> -->
    </div>
</template>

<script>
import TimeCardList from '../../components/TIME-CARD/TimeCardList.vue';
import TimeCardService from '../../services/TimeCardService';

export default{
    components: {
        TimeCardList
    },
    data(){
      return{
        dateFilter: '',
        timeCards: []
      }
    },
    computed: {
    filteredTimeCards() {
        if (this.dateFilter) {
            const selectedDate = new Date(this.dateFilter + 'T00:00:00');
            selectedDate.setHours(0, 0, 0, 0);
            // console.log("Selected Date Filter:", this.dateFilter);
            // console.log("Selected Date (Start of Day):", selectedDate);

            return this.timeCards.filter(card => {
                const cardDate = new Date(card.dateTimeIn || card.clockInTime);
                cardDate.setHours(0, 0, 0, 0); 
                // console.log("Card Date:", card.dateTimeIn || card.clockInTime, "Card Date (Start of Day):", cardDate);

                return selectedDate.getTime() === cardDate.getTime();
            });
        }
        return this.timeCards;
    }
},
    created(){
        this.getTimeCards();
    },
    methods: {
        getTimeCards(){
            TimeCardService.getAllTimeCardsByUserId().then((response) => 
            {this.timeCards = response.data;});
        }
    }


}
</script>

<style scoped>
.content {
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.small-container a {
  text-decoration: none;
  color: #3e4a3d;
}

.small-container h3 {
  margin: 0;
}

.small-container h5 {
  margin-bottom: 0;
}
</style>