<template >
<div class="container" >
    <row class="row-h">
        <form class="col-6 d-flex offset-3 my-5" @submit.prevent="searchPhoto">
                <input type="text" class="form-control form-input" v-model="search" placeholder="Cerca la foto...">
        </form> 
        <div class="row flex-wrap my-5">		
            <div class="col-xl-4 col-md-6 mt-4 " v-for="photo in searchPhoto" :key="photo.id" @click="$emit('openPhoto', photo.id)">
                
                <div class="card profile-card-5" >
                    <div class="card-img-block">
                        <img class="card-img-top" :src="photo.url" alt="Card image cap" height="280px"/>
                    </div>
                    <div class="card-body pt-0 mt-3 text-center text-white fw-bold">
                        <h4 class="card-title">{{ photo.name }}</h4>
                    </div>
                    
                </div>
        
            </div>	
        </div>
    </row>
</div>
</template>
<script setup>
import { defineProps, ref, computed } from 'vue';  

const emits = defineEmits(["openPhoto"]);


const search = ref('');
const searchPhoto = computed(()=>{
const searched = search.value.toLowerCase().trim();
return searched ? props.photos.filter(photo=>photo.name.toLowerCase().includes(searched)) : props.photos 

  
});

const props = defineProps({
    photos: {
        type: Array,
        required: true
    }
});


</script>
<style  >
    
</style>