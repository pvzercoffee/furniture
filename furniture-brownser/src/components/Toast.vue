<template>
<div v-show="isShow" class="toast">
  <p class="toast-text">{{ text }}</p>
</div>
</template>

<script setup lang="ts">
import { toastStore } from '@/store/toastStore';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';


let property = toastStore();

const {text,isShow} = storeToRefs(property);



</script>

<style scoped>
.toast{
  /* width:100%;
  height:200px; */
  background-color:#aad08d;
  position: fixed;
  z-index: 9999;
  border-radius: 5px;
  left:50%;
  top:40%;
  border: 1px solid #666;
  transform: translate(-50%,-50%);
}

.toast-text{
  margin:5px 15px 5px 15px;
}
</style>