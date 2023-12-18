<template>
    <div class="container">
		<div class="row my-5 row-h">
            <div class="col-12 text-start">
                <a class="btn btn-sm btn-warning" @click="$emit('goBack')">Back</a>
            </div>
			<div class="col-12 text-center">
				<h2>{{ photo.name }}</h2>
				<div class="col-12 d-lg-flex  my-5">										
					<img :src="photo.url" class="col-lg-8 col-12">
					
					<div class="my-4 col-lg-4 col-12 ms-3 text-start">
						<div class="mt-3 mb-5">
							<h5>Descrizione:</h5>
							<p>
								{{ photo.description }}
							</p>
						</div>
						<div>						
						<h5>Categorie:</h5>
						<ul class="list-unstyled fw-bold">
							<li v-for="category in photo.categories"                          
							:key="category.id">{{ category.name }}</li>
						</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script setup>
const emits = defineEmits(['goBack']);
    const props = defineProps({
        photo:{
            type:Object,
            required:true
        }
    });
</script>
<style >

</style>
