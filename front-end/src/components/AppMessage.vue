<template>

    <div class="container row-h">
        <div class="row mt-5 ">
            <div class="my-3 text-start col-12">
                <button class="btn btn-sm btn-warning" type="button" @click="$emit('back')">Back</button>
            </div>
            <div class="col-12 text-center ">
                <h1>Contattaci</h1>
            </div>
            <div class="col-6 offset-3 card p-5">
                <form class="fw-bold text-white" @submit.prevent="submit">

                    <div class="form-group my-3">
                        <label for="name" class="d-block">Email</label>
                        <input type="email" name="email" id="name" class="form-control" v-model="newMessage.email" required>
                    </div>
                    <div class="form-group my-3">
                        <label for="description">Testo</label>
                        <textarea name="text" id="description" class="form-control"  v-model="newMessage.message" required/>
                    </div>
                    <div class="my-5">
                        <button class="btn btn-sm btn-success" type="submit">Submit</button>
                    </div>
                  
                </form>
            </div>
        </div>
    </div>
</template>
<script setup>
import { defineEmits, ref } from 'vue';
import axios from 'axios';

const emits = defineEmits(["back", "created"]);
const newMessage = ref({
    email:'',
    message:'',
})


const submit = async () => {
    try {
   
        const data = await axios.post("http://localhost:8080/api/contact", newMessage.value
        );
       

        emits("created");
        emits("thank");
        
        console.log(data);
    } catch (error) {
        console.error(error);
    }
};

</script>