<template>
    <!-- UNDER CONSTRUCTION -- THIS IS NOT IMPLEMENTED YET -->
    <div class="upload-container">
      <input 
        type="file" 
        @change="handleFileUpload" 
        ref="fileInput"
        accept=".pdf,.doc,.docx"
        class="hidden"
      />
      <button 
        @click="$refs.fileInput.click()"
        class="upload-btn">Select File</button>

      <span v-if="selectedFile">{{ selectedFile.name }}</span>
      <button 
        v-if="selectedFile" @click="uploadFile" class="submit-btn" :disabled="uploading">
        {{ uploading ? 'Uploading...' : 'Upload File' }}
      </button>
      <div v-if="error" class="error-message">
        {{ error }}
      </div>
      <div v-if="success" class="success-message">
      {{ success }}
    </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'FileUpload',
    data() {
      return {
        selectedFile: null,
        uploading: false,
        error: null,
        success: null
      };
    },
    methods: {
      handleFileUpload(event) {
        this.selectedFile = event.target.files[0];
        this.error = null;
      },
      async uploadFile() {
        if (!this.selectedFile) {
          this.error = 'Please select a file first';
          return;
        }
  
        try {
          this.uploading = true;
          this.error = null;
  
          const formData = new FormData();
          formData.append('file', this.selectedFile);
  
          const response = await axios.post('/api/upload', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
  
          // Emit the file path received from the server
          this.$emit('upload-success', response.data.filePath);
          
          // Reset the form
          this.selectedFile = null;
          this.$refs.fileInput.value = '';
          
        } catch (error) {
          this.error = error.response?.data?.message || 'Error uploading file';
        } finally {
          this.uploading = false;
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .upload-container {
    padding: 20px;
  }
  
  .hidden {
    display: none;
  }
  
  .upload-btn, .submit-btn {
    padding: 8px 16px;
    margin: 0 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
    background-color: #fff;
    cursor: pointer;
  }
  
  .submit-btn:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
  
  .error-message {
    color: red;
    margin-top: 10px;
  }
  </style>
  
  // Backend Express server code (server.js)
  const express = require('express');
  const multer = require('multer');
  const path = require('path');
  const fs = require('fs');
  
  const app = express();
  
  // Configure multer for file upload
  const storage = multer.diskStorage({
    destination: function (req, file, cb) {
      const uploadPath = path.join(__dirname, '../public/assets/documents');
      
      // Create directory if it doesn't exist
      if (!fs.existsSync(uploadPath)) {
        fs.mkdirSync(uploadPath, { recursive: true });
      }
      
      cb(null, uploadPath);
    },
    filename: function (req, file, cb) {
      // Generate unique filename
      const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9);
      cb(null, file.fieldname + '-' + uniqueSuffix + path.extname(file.originalname));
    }
  });
  
  // File filter function
  const fileFilter = (req, file, cb) => {
    // Accept only specific file types
    const allowedTypes = ['.pdf', '.doc', '.docx'];
    const ext = path.extname(file.originalname).toLowerCase();
    
    if (allowedTypes.includes(ext)) {
      cb(null, true);
    } else {
      cb(new Error('Invalid file type. Only PDF and DOC files are allowed.'));
    }
  };
  
  const upload = multer({ 
    storage: storage,
    fileFilter: fileFilter,
    limits: {
      fileSize: 5 * 1024 * 1024 // 5MB limit
    }
  });
  
  // File upload endpoint
  app.post('/api/upload', upload.single('file'), (req, res) => {
    try {
      if (!req.file) {
        return res.status(400).json({ message: 'No file uploaded' });
      }
  
      // Generate the relative path for database storage
      const relativePath = `/assets/documents/${req.file.filename}`;
  
      // Here you would typically save the relativePath to your database
      // database.save({ filePath: relativePath });
  
      res.json({ 
        message: 'File uploaded successfully',
        filePath: relativePath
      });
    } catch (error) {
      res.status(500).json({ 
        message: 'Error uploading file',
        error: error.message 
      });
    }
  });
  
  // Error handling middleware
  app.use((error, req, res, next) => {
    if (error instanceof multer.MulterError) {
      if (error.code === 'LIMIT_FILE_SIZE') {
        return res.status(400).json({
          message: 'File size too large. Max size is 5MB'
        });
      }
    }
    res.status(500).json({
      message: error.message || 'Something went wrong'
    });
  });