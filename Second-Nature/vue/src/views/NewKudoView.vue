<template>
    <h1>Give Kudos</h1>
    <div class="container">
        <form id="give-kudos-form" @submit.prevent="onSubmit">
            <!-- Dropdown list of users -->
            <div class="form-group">
                <label for="userSelect">Select User:</label>
                <select v-model="kudo.receiverUserId" id="userSelect" required>
                    <option value="" disabled>Select a user</option>
                    <option v-for="user in users" :key="user.userId" :value="user.userId">
                        {{ user.firstName }} {{ user.lastName }}
                    </option>
                </select>
            </div>

            <!-- Title text field -->
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" v-model="kudo.title" id="title" required />
            </div>

            <!-- Note text field -->
            <div class="form-group">
                <label for="note">Note:</label>
                <textarea v-model="kudo.notes" id="note" rows="4" required></textarea>
            </div>

            <!-- Submit button -->
            <button type="submit">Send this Kudo!</button>
        </form>
    </div>
</template>

<script>
import KudosService from '../services/KudosService';

export default {
    data() {
        return {
            users: [],
            kudo: {
                title: '',
                notes: '',
                giverUserId: '',
                receiverUserId: '',
                date: '',
                archive: ''
            },
            currentDate: new Date().toISOString().slice(0, 10),
        }
    },
    created() {
        this.fetchUsers();
    },
    methods: {
        fetchUsers() {
            KudosService.getAllOtherUsers()
                .then(response => {
                    this.users = response.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
        onSubmit() {
            this.kudo.giverUserId = this.currentUserId;
            this.kudo.date = this.currentDate;
            KudosService.giveKudos(this.kudo)
                .then(response => {
                    console.log(response.data);
                    this.title = '';
                    this.note = '';
                    this.selectedUserId = '';
                    alert('Kudo sent!');
                    this.$router.push( {    name: 'kudos' } );
                })
                .catch(error => {
                    console.log(error);
                });
        }
    },
    computed: {
        currentUserId() {
            return this.$store.state.user.userId;
        }
    }
}
</script>

<style></style>