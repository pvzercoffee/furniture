<template>
  <div class="showMessage">
    <div class="container">

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


      </div>
  </div>

</template>

<script setup lang="ts">

import { messageStore } from '@/store/messageStore';
import { userStore } from '@/store/userStore';
import { onMounted, reactive, watch } from 'vue';

let page = 1;

let messages = reactive(messageStore());
watch(()=>userStore().userInfo.token,(token)=>{
    if(token){
      messageStore().queryMessageAction(page)
      console.log("watch发起请求"+userStore().userInfo.token);
    }
  })

onMounted(()=>{
  if(userStore().userInfo.token) messageStore().queryMessageAction(page);
});
</script>

<style scoped>

.container{
  display: flex;
  flex-direction: column;
  font-family: '微软雅黑';
  margin-top: 50px;
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
</style>