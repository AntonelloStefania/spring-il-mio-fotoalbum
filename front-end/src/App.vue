<template>
  <AppHeader />
  <div v-if="photoActive == null">
    <Index :photos="photos"
       @open-photo="openPhoto"
       @search="search = true"/>
  </div>
  <div v-else>
    <photoShow
    :photo="photoActive"
    @goBack="goBack"
    />
  </div>

</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import Index from './components/Index.vue';
import AppHeader from './components/AppHeader.vue';
import photoShow from './components/photoShow.vue';

const photos = ref(null);
const photoActive = ref(null);
const search = ref(true);

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

  onMounted(indexPhoto);
</script>


<style lang="scss">
@use './styles/generals.scss' as *;

</style>
