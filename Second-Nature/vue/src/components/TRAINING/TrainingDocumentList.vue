<template>
    <div class="list-container" v-if="!pdfUrl">
        <div class="list-item" v-for="(doc) in documents" :key="doc.id" @click="viewDocument(doc.url)">
            {{ doc.name }}
        </div>    
    </div>
    <TrainingDocumentViewer :url="pdfUrl" v-if="pdfUrl" @clear="clearPdf"/>
</template>

<script>
import TrainingDocumentViewer from '../../components/TRAINING/TrainingDocumentViewer.vue';

export default {
    data() {
        return {
            documents: [
                { name: 'Stihl Backpack Blower 350, 430', url: 'https://www.stihlusa.com/webcontent/cmsfilelibrary/instructionmanuals/stihl-br-350-430-owners-instruction-manual.pdf' },
                { name: 'Stihl Chainsaw 009', url: 'https://cdnassets.stihlusa.com/1625858121-009manual.pdf' },
                { name: 'Stihl Blower BG 46', url: 'https://cdnassets.stihlusa.com/1625857590-bg46manual.pdf'},
                { name: 'Stihl Mower RM 655.0 V', url: 'https://cdnassets.stihlusa.com/1705942922-stihl-rm-655v-mower-owners-instruction-manual.pdf'},
                { name: 'Stihl Trimmer FE 55', url: 'https://cdnassets.stihlusa.com/1625863311-stihl-fe-55-owners-instruction-manual.pdf'},
            ],
            pdfUrl: '',
        };
    },
    components: {
        TrainingDocumentViewer
    },
    methods: {
    viewDocument(url) {
      this.pdfUrl = url;
    },
    clearPdf(){
      this.pdfUrl='';
    }
  },
}
</script>

<style scoped>
.list-container {
  display: flex;
  align-items: center;
  flex-direction: column;
  margin-top: 10px;
}

.list-item {
    background-color: white;
    border: 1px solid #a1af9f;
    border-radius: 10px;
    width: 100%;
    max-width: 400px;
    padding: 20px;
    box-shadow: -2px 2px 4px #a1af9f;
    box-sizing: border-box;
    margin-left: 5%;
    margin-right: 5%;
    margin-bottom: 5%;
    text-align: center;
}
</style>