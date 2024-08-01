<template>
    <div class="container">
        <h2>Give Kudos</h2>
        <form id="give-kudos-form" @submit.prevent="onSubmit">
            <!-- Dropdown list of users -->
            <div class="form-input-group">
                <label for="userSelect">To: </label>
                <select v-model="kudo.receiverUserId" id="userSelect" required>
                    <option value="" disabled>Select a Team Member</option>
                    <option v-for="user in users" :key="user.userId" :value="user.userId">
                        {{ user.firstName }} {{ user.lastName }}
                    </option>
                </select>
            </div>

            <!-- Title text field -->
            <div class="form-input-group">
                <label for="title">Title: </label>
                <input type="text" v-model="kudo.title" id="title" required />
            </div>

            <!-- Note text field -->
            <div class="form-input-group">
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

<style>
button {
    height: 50px;
}

form {
    display: flex;
    flex-direction: column;
    background-color: white;
    border: 1px solid #a1af9f;
    border-radius: 10px;
    padding: 20px;
    width: 90%;
    align-content: space-between;
    justify-content: space-between;
}

h2 {
    text-align: center;
}

#note {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    width: 100%;
    height: 20em;
    resize: none;
}

.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px 5%;
}
</style>