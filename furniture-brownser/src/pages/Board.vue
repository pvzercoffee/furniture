<template>
  <div class="page-board">
      <Carousel />
      <h1 class="path">首页>>在线留言</h1>
      <div class="board">
          <h2 class="title-big">在线留言</h2>
          <hr class="color-hr">
          <div class="board-card">
              <div class="card-left">
                <ul class="serve-list">
                      <li v-for="category in messageStore().itemList" :key="category.id"><button class="item-btn">{{ category.name }}</button></li>
                  </ul>
                  <div class="left-img"></div>
              </div>
              <div class="card-empty"></div>
              <div class="card-right">
                  <h3 class="text-big">您可以留下您宝贵的意见，我们会在第一时间回复您，谢谢关注！</h3>
                  <form ref="mainForm">
                      <h4 class="font-text">您的姓名<span class="redstar">*</span></h4>
                      <input type="text" v-model="submitInfo.name" required autocomplete="name">
                      <h4 class="font-text">您的电话<span class="redstar">*</span></h4>
                      <input type="text"v-model="submitInfo.telephone" required autocomplete="tel">
                      <h4 class="font-text">您的邮箱<span class="redstar">*</span></h4>
                      <input type="text" v-model="submitInfo.email" required autocomplete="email">
                      <h4 class="font-text">咨询项目<span class="redstar">*</span></h4>

                      <span class="font-text">
                        <input type="checkbox" v-model="isSelectAll">全选
                      </span>

                      <span class="font-text" v-for="category in messageStore().itemList" :key="category.id">
                        <input type="checkbox" v-model="category.status">{{ category.name }}
                      </span>

                      <h4 class="font-text"style="margin-top: 60px;">咨询内容<span class="redstar">*</span></h4>
                      <p class="font-text" id="word_count" :style="{'color':hintColor}">{{ hint }}</p>
                      <textarea id="msg_area" required v-model="submitInfo.text"></textarea>
                      <br>
                      <input type="button" value="提交" @click="submit">
                  </form>

              </div>
          </div>
          <!-- 留言展示 -->
          <ShowMessage :key="messageKey"/>
      </div>
    </div>
</template>
<script setup lang="ts">

import Carousel from '@/components/Carousel.vue';
import ShowMessage from '@/components/ShowMessage.vue';
import { HintColors } from '@/constants/HintColors';
import { messageStore } from '@/store/messageStore';
import { toastStore } from '@/store/toastStore';
import { userStore } from '@/store/userStore';
import '@/styles/board.css'
import { computed, onMounted, reactive, ref, watch } from 'vue';

let messageKey = ref(0);

//提交给后端的信息
let submitInfo = reactive({
    name:'',
    telephone:'',
    email:'',
    text:'',
    itemList:<number[]>[]
});

//消息提示
let toast = toastStore();

//watch监测全选按钮，实现全反选
const isSelectAll = ref(false);

watch(isSelectAll,()=>{
messageStore().itemList.forEach((value,index)=>{
  value.status = isSelectAll.value;
});
})

//computed响应式计算还能写几个字
const maxInput = 100;
let hintColor = ref(HintColors.normal)

let hint = ref(`你还能输入${maxInput}个字`);

const words = computed(()=>{
return maxInput - submitInfo.text.length;
});

//渲染字数情况给页面
watch(words,()=>{
if(words.value < 0)
{
  hint.value = `您已超出${-words.value}个字`
  hintColor.value = HintColors.illegal
  return;
}

hint.value = `您还可以输入${words.value}个字`
hintColor.value = HintColors.legal

});


let mainForm = ref<HTMLFormElement|null>(null);

//表单验证
const verify = ()=>{

  const {itemList} = messageStore();
  const {isLogin} = userStore();

  if(!isLogin){
    toast.show("登录后才能留言");
    return false;
  }
  if(!mainForm.value?.reportValidity()){
    toast.show("请填写完整信息");
    return false;
  }
  if(words.value < 0){
    toast.show("超过允许字数");
    return false;
  }
  //arr.some(item=>bool)：当数组元素有一个满足bool表达式，则返回true
  if(!itemList.some(item => item.status)){
    toast.show("请至少选择一个咨询项目");
    return false;
  }

  return true;
}

//提交留言
const submit = ()=>{

  if(!verify()) return;
  const {itemList} = messageStore();

  //arr.filter(item=>bool)：剔除所有不符合bool的元素
  //arr.map(item=>bool)：返回一个属性组成的新arr

  submitInfo.itemList = itemList
    .filter(item => item.status)
    .map(item=>item.id);

  messageStore().addMessageAction(submitInfo);
  toast.show("留言发表成功");
  //加载留言列表
  messageKey.value++;

  //发表后清除表单
  submitInfo.email = submitInfo.name = submitInfo.telephone = submitInfo.text = '';
  submitInfo.itemList = [];

}

//加载板块
onMounted(()=>{
    messageStore().queryItemAction();

});
</script>
