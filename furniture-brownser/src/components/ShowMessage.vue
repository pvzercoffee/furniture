<template>
  <div class="showMessage" v-show="userStore().isLogin">
    <div class="container">
      <div class="top">
        <p class="title">共有{{ messages.messageTotal }} 条留言</p>
        <select class="top-select">
          <option value="all">全部留言</option>
          <option value="self">只看我的</option>
        </select>
      </div>
      <div :key="messages.update">
        <MessageBody v-for="value in messages.messageList" :key="value.id"  :data="value"/>
      </div>

      <button type="button" class="view-button" :key="messages.update" :disabled="acceptRefresh" @click="showMoreMessage">{{ remainMessage >= 1 ? `查看剩余${remainMessage}条留言` : '留言全部加载完毕' }}</button>
      </div>
  </div>

</template>

<script setup lang="ts">

import { messageStore } from '@/store/messageStore';
import { userStore } from '@/store/userStore';
import { computed, onMounted, onUnmounted, reactive, ref, watch } from 'vue';
import MessageBody from './MessageBody.vue';

let messages = messageStore();
let acceptRefresh = ref(false);

//计算剩余未加载的留言数
const remainMessage = computed(()=>{
  return messages.messageTotal- messages.messageList.length;
});

//加载留言
const showMoreMessage = async ()=>{
  acceptRefresh.value = true;
  if(remainMessage.value >= 1){
    messages.page++;
    await messageStore().queryMessageAction(messages.page)
  }
  acceptRefresh.value = false;
}

//登录后立即加载留言
watch(()=>userStore().userInfo.token,(token)=>{
    if(token){
      messageStore().queryMessageAction(messages.page)
    }
})

onMounted(()=>{
  if(userStore().userInfo.token) messageStore().queryMessageAction(messages.page);
});

//卸载组件时还原评论
onUnmounted(()=>{
  messages.messageTotal = 0;
  messages.page = 1;
  messages.messageList = [];
});
</script>

<style scoped>

.container{
  display: flex;
  flex-direction: column;
  font-family: '微软雅黑';
  margin-top: 50px;
}
.top{
  display: flex;
}
.top-select{
  font-size: 20px;
  border: 0;
  margin-left: auto;
  background-color: #ffffff00;
}
.title{
  font-size: 30px;
  margin: 40px 0 40px 20px;
}

.view-button{
  width: 250px;
  margin: auto;
  height: 35px;
  margin-top: 25px;
  background-color: rgb(230, 213, 116);
  border: 0;
}
.view-button:hover{
  background-color: rgb(213, 197, 109);
}
</style>