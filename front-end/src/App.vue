<template>
  <AppHeader />
  <div v-if="photoActive == null && sendingMessage == false">
    <div class="col-8 offset-2 my-5">
      <button class="btn btn-primary" @click="sendingMessage = true">Contattaci</button>
    </div>
    <Index :photos="photos"
       @open-photo="openPhoto"
       @search="search = true"/>
       <div class="col-12">
    </div>
  </div>
  <div v-else-if="photoActive != null">
    <photoShow
    :photo="photoActive"
    @goBack="goBack"
    />
  </div>
  <div>
    <AppMessage @back="sendingMessage = false"
      @created="sendingMail"/>
  </div>

</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import Index from './components/Index.vue';
import AppHeader from './components/AppHeader.vue';
import photoShow from './components/photoShow.vue';
import AppMessage from './components/AppMessage.vue';

const photos = ref(null);
const photoActive = ref(null);
const search = ref(true);
const sendingMessage = ref(false);

  const indexPhoto = async()=> {
    const data = await axios.get("http://localhost:8080/api/photo");
    photos.value = data.data;
  };

  const openPhoto = (id) => {
    photos.value.forEach((photo)=>{
      if(photo.id == id){
        photoActive.value = photo;
      }
    });
  };
  const goBack = () => {
    photoActive.value = null;
      indexPhoto();
  }
  const sendingMail = ()=>{
    sendingMessage.value = false;
    indexPhoto();
  }

  onMounted(indexPhoto);
</script>


<style lang="scss">
@use './styles/generals.scss' as *;

</style>
