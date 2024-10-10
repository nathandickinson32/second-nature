<template>
  <div class="content">
    <h1>Training</h1>
        <ul>
      <li v-for="(doc, index) in documents" :key="index" @click="viewDocument(doc.url)">
        {{ doc.name }}
      </li>
    </ul>

    <div v-if="pdfUrl">
      <h2>Document Viewer</h2>
      <pdf :src="pdfUrl" />
    </div>
  </div>
</template>

<script>
import { pdf } from 'vue-pdf';

export default {
  components: {
    pdf,
  },
  data() {
    return {
      documents: [
        { name: 'Stihl Backpack Blower', url: '../public/assets/documents/Stihl Backpack Blower Manual.pdf' },
        // { name: 'Document 2', url: '/path/to/document2.pdf' },
        // Add more documents as needed
      ],
      pdfUrl: null,
    };
  },
  methods: {
    viewDocument(url) {
      this.pdfUrl = url;
    },
  },
};
</script>