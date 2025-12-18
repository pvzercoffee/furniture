<template>
<div class="card">
  <div class="good" :style="{backgroundImage:`url(${getImageUrl(`photos/${img}`)})`}"></div>
  <p class="good-text">￥{{ price }}</p>
  <p class="good-text">{{ name }}</p>
</div>
</template>
<script setup lang="ts">
import resourceTool from '@/utils/resourceTool';

const {getImageUrl} = resourceTool;
const props = defineProps(['value']);
const {img,price,name} = props.value;

</script>
<style scoped>
.good{
  /* flex: 1; */
  background-repeat: no-repeat;
  background-size: 100% 100% ;
  width: 100%;
  height: 280px;
  margin-bottom:5px;
  border-bottom: 1px solid #888;
}
.good-text{
  margin: 0;
  margin-left: 20px;
  color: #000;
  font-weight: 700;
  margin-top: auto;
  font-size: 20px;
}
</style>