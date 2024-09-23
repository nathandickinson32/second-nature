<template>
    <div class="content">
        <!-- <div class="filter-section">
            <label @click="showAllTimeCards" class="clickable-label">Show All</label>
            <span class="separator"> | </span>
            <label @click="showClockIns" class="clickable-label">Clock Ins</label>
            <span class="separator"> | </span>
            <label @click="showClockOuts" class="clickable-label">Clock Outs</label>
            <span class="separator"> | </span>
            <label @click="showMealIns" class="clickable-label">Meal Ins</label>
            <span class="separator"> | </span>
            <label @click="showMealOuts" class="clickable-label">Meal Outs</label>
        </div> -->

        <!-- <div class="time-card-list" v-if="filteredTimeCard.length === 0">No kudos have been entered yet.</div>
        <div class="time-card-list" v-else>
            <TimeCard v-for="timeCard in filteredTimeCards" :key="timeCard.time_card_Id" :timeCard="timeCard"></TimeCard>
        </div> -->
    </div>
</template>

<script>
/*
import TimeCard from './TimeCard.vue';
import TimeCardService from '../../services/TimeCardService';
*/

/*
export default {
    components: {
        TimeCard
    },
    data(){
        return {
            timeCards: [],
            filterType: 'all'
        }
    },
    created(){
        this.getTimeCards
    },
    computed: {
        filteredTimeCards() {
            if (this.filterType === 'all') {
                return this.timeCards;
            }
            else if (this.filterType === 'clockIns') {
                return this.timeCards.filter(timeCard => timeCard.hour_type === clock_in);
            }
            else if (this.filterType === 'clockOuts') {
                return this.timeCards.filter(timeCard => timeCard.hour_type === clock_out);
            }
            else if (this.filterType === 'mealIns') {
                return this.timeCards.filter(timeCard => timeCard.hour_type === meal_in);
            }
            else if (this.filterType === 'mealOuts') {
                return this.timeCards.filter(timeCard => timeCard.hour_type === meal_out);
            }
            return []; // Default return for unexpected filterType
        },
        currentUserId() {
            return this.$store.state.user.id;
        }
    },
    methods: {
        getTimeCards() {
            TimeCardService.getTimeCardsById()
                .then(
                    (response) => {
                        console.log(response.data);
                        this.timeCards = response.data.filter(timeCard => timeCard.archived === false);
                    }
                );
        },
        showAllTimeCards() {
            this.filterType = 'all';
        },
        showClockIns() {
            this.filterType = 'clockIns';
        },
        showClockOuts() {
            this.filterType = 'clockOuts';
        },
        showMealIns() {
            this.filterType = 'mealIns';
        },
        showMealOuts() {
            this.filterType = 'mealOuts';
        },
    }
}
*/
</script>

<style scoped>

</style>