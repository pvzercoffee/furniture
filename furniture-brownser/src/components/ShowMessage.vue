<template>
  <div class="showMessage">

    <div class="container">
      <p class="title">共有{{ messages.messageTotal }} 留言</p>
      <div class="message" v-for="msg in messages.messageList" :key="msg.id">
        <div class="msg-header">
          <div class="msg-avatar"></div>
          <p class="msg-username">{{ msg.username }}</p>
        </div>

        <div class="msg-info">
          <p class="msg-time">{{ msg.createTime }}</p>
        </div>

        <div class="msg-body">
          <p class="msg-text">
            {{ msg.text }}
          </p>
        </div>

        <div class="msg-items">
          <div class="msg-item"  v-for="item in msg.itemList" :key="item"><p class="item-name">{{ item }}</p></div>

        </div>
      </div>
      <button type="button" class="view-button" @click="showMoreMessage">{{ remainMessage >= 1 ? `查看剩余${remainMessage}条留言` : '留言全部加载完毕' }}</button>

      </div>
  </div>

</template>

<script setup lang="ts">

import { messageStore } from '@/store/messageStore';
import { userStore } from '@/store/userStore';
import { computed, onMounted, onUnmounted, reactive, watch } from 'vue';

let page = 1;

const remainMessage = computed(()=>{
  return  messages.messageTotal- messages.messageList.length;
});

const showMoreMessage = ()=>{
  if(remainMessage.value >= 1){
    page++;
    messageStore().queryMessageAction(page)
  }
}

let messages = reactive(messageStore());
watch(()=>userStore().userInfo.token,(token)=>{
    if(token){
      messageStore().queryMessageAction(page)
    }
})

onMounted(()=>{
  if(userStore().userInfo.token) messageStore().queryMessageAction(page);
});

//卸载组件时还原评论
onUnmounted(()=>{
  messages.messageTotal = 0;
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
.title{
  font-size: 30px;
  margin: 40px 0 40px 40px;
}
.message{
  display: flex;
  background-color: #00000009;
  border-radius: 15px;
  flex-direction: column;
  padding: 20px 5% 20px 5%;
  margin: 0 20px 20px 20px;
}

.msg-header{
  display: flex;
  /* margin: 20px; */
}
.msg-avatar{
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-image: url('@/images/div1.jpeg');
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.msg-username{
  margin-left: 15px;
}
.msg-time{
  color: #444;
  font-weight: 300;
  font-family: '微软雅黑';
}
.msg-text{
  font-weight: 300;

}
.msg-items{
  display: flex;
  flex-direction:row;
  flex-wrap: wrap;
}
.msg-item{
  background-color: rgb(253, 235, 216);;
  margin: 10px 15px 0 0;
  line-height: 8px;
  font-size: 12px;
  border-radius: 50px;
  padding: 0 13px 0 13px;
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