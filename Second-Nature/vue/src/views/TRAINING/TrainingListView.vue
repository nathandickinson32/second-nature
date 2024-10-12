<template>
  <div class="content">
    <h1>Training</h1>
        <ul v-if="!pdfUrl">
          <li v-for="(doc) in documents" :key="doc.id" @click="viewDocument(doc.url)">
            {{ doc.name }}
          </li>
        </ul>
    <TrainingDocumentViewer  :url="pdfUrl" v-if="pdfUrl" @clear="clearPdf"/>
  </div>
</template>

<script>
import TrainingDocumentViewer from '../../components/TRAINING/TrainingDocumentViewer.vue';

export default {
  data() {
    return {
      documents: [
        { name: 'Stihl Backpack Blower', url: 'https://www.stihlusa.com/webcontent/cmsfilelibrary/instructionmanuals/stihl-br-350-430-owners-instruction-manual.pdf' },
        // { name: 'Document 2', url: '/path/to/document2.pdf' },
        // Add more documents as needed
      ],
      pdfUrl: '',
    };
  },
  components: {
    TrainingDocumentViewer
  },
  methods: {
    viewDocument(url) {
      // console.log("Here's our attempt at looking at the document" + JSON.stringify(url));
      this.pdfUrl = url;
      // console.log(JSON.stringify(this.pdfUrl));
    },
    clearPdf(){
      this.pdfUrl='';
    }
  },
};
</script>

<style scoped>
embed {
  width: 90%;
  height: 100vh;
}

.content{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

</style>