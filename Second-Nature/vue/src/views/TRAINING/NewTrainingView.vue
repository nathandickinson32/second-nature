<template>
  <div class="content">
       <h2>Create Training</h2>
        <div class="large-container">
            <form id="new-training-form" @submit.prevent="onSubmit">
                <!-- Upload the new file -->
                <input type="file" @change="onFileChange" required/>
                <!-- <button @click="uploadFile">Upload</button> -->
                <!-- <div v-if="createTraining.fileUrl">Uploaded File: <a :href="createTraining.fileUrl" target="_blank">View Document</a></div> -->
 

                <!-- Title text field -->
                <div class="form-input-group">
                    <label for="title">Title: </label>
                    <input type="text" v-model="createTraining.title" id="title" required />
                </div>

                <!-- Category text field -->
                <div class="form-input-group">
                    <textarea v-model="createTraining.category" id="category" rows="1" required></textarea>
                </div>

                <!-- Submit button -->
                <button type="submit">Add this document</button>
            </form>
        </div>


  </div>
</template>

<script>
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
    width: 200px;
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
</style>