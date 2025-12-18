<template>
<div v-show="isShow" class="dialog">
    <div class="dialog-main">
        <div class="dialog-head">
            <span class="dialog-title">{{ name }}</span>
            <button class="dialog-button" @click="hide">关闭</button>
        </div>
        <img :src="img" class="dialog-detail">
    </div>
</div>
</template>

<script setup lang="ts">
import { ref, toRefs } from 'vue';

const props = defineProps<{
  message:{
    name:string,
    img:string,
    price:number
  }
}>();

const {name,img} = toRefs(props.message);

let isShow = ref(false);

const show = () => {
  isShow.value = true;
}

function hide(){
  isShow.value = false;
}

//暴露给父组件控制
defineExpose({
  show,
  hide
});
</script>

<style scoped>

.dialog-main{
    width: 480px;
    height: 480px;
    margin:12% auto;
    background-color: #ddd;
    box-shadow: 0px 10px 10px 10px #666;
}
.dialog-detail{
    width: 100%;
    height: 100%;

}
.dialog-head{
    display: flex;
}
.dialog-button{
    margin:0;
    padding: 5px;
    margin-left: auto;
    font-size: 15px;
    border: 0;
    background-color: #ddd;
}
.dialog-title{
    font-size: 14px;
    line-height: 30px;
}
@media (max-width: 768px) {
    .dialog-main{
        width: 300px;
        height: 50%;
        margin-top: 50%;
        /* transform: translate(0,-50%); */
    }
    .dialog-detail{
      height: auto;
    }
}

</style>
