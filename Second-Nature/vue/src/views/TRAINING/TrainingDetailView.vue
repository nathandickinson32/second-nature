<template>
    <TrainingDocumentViewer :url="pdfUrl" />
    <FooterVue />
</template>

<script>

import FooterVue from '../../components/Footer.vue';
import TrainingDocumentViewer from '../../components/TRAINING/TrainingDocumentViewer.vue';

export default {
    data() {
        return {
            //Placeholder of the hard-coded stuff for now. Will delete this for 
            //server-sourced data in near future.
            documents: [
                { name: 'Stihl Backpack Blower', url: 'https://www.stihlusa.com/webcontent/cmsfilelibrary/instructionmanuals/stihl-br-350-430-owners-instruction-manual.pdf' },
                // { name: 'Document 2', url: '/path/to/document2.pdf' },
                // Add more documents as needed
            ],
            pdfUrl: '',
        };
    },
    created() {
        this.pdfUrl = this.documents[0].url;
    },

    // computed: {
    //     pdfUrl() {
    //         return this.pdfUrl;
    //     }
    // },
    components: {
        TrainingDocumentViewer
    },
    methods: {
        viewDocument(url) {
            this.pdfUrl = url;
        },
        clearPdf() {
            this.pdfUrl = '';
        }
    }

    // TrainingResource (Model)
    // {
    // 	"training_resource_id" : Integery / Number,
    // 	"title" : String
    // 	"category" : String (options include: "general", "policies", "winter", "spring", "summer", etc...)
    // 	"content" : String,
    // 	"resource_source" : String (upload location),
    // 	"entered_on_date" : Time Stamp,
    // 	"entered_by_user_id" : Intiger,
    // 	"updated_on_date" : Time Stamp,
    // 	"updated_by_user_id" : Intiger,
    // 	"archived" : Boolean (Defaults to false),
    // 	"archived_notes" : String (Required once the archived option is selected)
    // }
}
</script>

<style></style>