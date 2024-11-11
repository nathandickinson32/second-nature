<template>
  <div class="content">
       <h2>Add New Training Document</h2>
        <div class="large-container">
            <form id="new-training-form" @submit.prevent="onSubmit">
                <!-- <button @click="uploadFile">Upload</button> -->
                <!-- <div v-if="createTraining.fileUrl">Uploaded File: <a :href="createTraining.fileUrl" target="_blank">View Document</a></div> -->

                <!-- Title text field -->
                <div class="form-input-group">
                    <label class="title-label" for="title">Title: </label>
                    <input type="text" v-model="createTraining.title" id="title" required />
                </div>

                <!-- Category text field -->
                <div class="form-input-group">
                    <label class="category-label" for="category">Category: </label> 
                    <input v-model="createTraining.category" id="category" rows="1" required/>
                </div>

                <!-- Upload the new file -->
                <input class="add-file" type="file" @change="onFileChange" required/>

                <!-- Submit button -->
                <button type="submit">Add Document</button>
            </form>
        </div>
  </div>
  <Footer/>
</template>

<script>
import Footer from '../../components/Footer.vue';
import Trainingservice from '../../services/Trainingservice';

export default {
    data() {
        return {
            createTraining:{
                title: '',
                category: '',
                file: null,
                fileUrl: null
            },
            filePresent: false,
        };
    },
    components: {
        Footer
    },
    methods: {
        onFileChange(event) {
        this.createTraining.file = event.target.files[0];
        this.createTraining.fileUrl = URL.createObjectURL(this.createTraining.file); // For preview (if needed)
        this.filePresent = true;
        },
        onSubmit() {
            const formData = new FormData();
            formData.append('file', this.createTraining.file);

                try {
                    Trainingservice.createTraining(this.createTraining)
                    .then(response => {
                    if (response.ok) {
                    console.log('File uploaded successfully:', response.data);
                    } else {
                    console.error('File upload failed:', response.statusText);
                    }})
                } catch (error) {
                    console.error('Error uploading file:', error);
                }
        }
    },


    
// CreateTrainingResourceDTO
// {
// 	"title" : String
// 	"category" : String (options include: "general", "policies", "winter", "spring", "summer", etc...)
// 	"content" : String,
// 	"resource_source" : String (upload location),
// }
// // Entered on date, and user id is handled on the backend Create controller.
}

</script>

<style>
button {
    height: 50px;
    width: 150px;
}

h2 {
    text-align: center;
}

input {
    width: 100%;
}

label {
    margin-right: 10px;
}

#note {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    width: 100%;
    height: 10em;
    resize: none;
    border-radius: 5px;
}

.content {
    flex-direction: column;
    align-items: center;
}

.large-container {
    width: 400px;
}

.add-file {
    margin-top: 10px;
    margin-bottom: 15px;
}

.title-label {
    margin-right: 44px;
}

</style>