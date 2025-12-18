<template>
  <div class="showMessage" v-show="userStore().isLogin">
    <div class="container">
      <div class="top">
        <p class="title">共有{{ msgs.messageTotal > 0 ? msgs.messageTotal : '...' }} 条留言</p>
        <select class="top-select" @change="changeShowArea" v-model="showArea">
          <option value="all">全部留言</option>
          <option value="self">只看我的</option>
        </select>
      </div>
      <div>
        <MessageBody v-for="value in msgs.messageList" :key="value.id"  :data="value"/>
      </div>

      <button type="button" class="view-button"
        :disabled="acceptRefresh"
        @click="showMoreMessage">{{ remainMessage >= 1 ? `查看剩余${remainMessage}条留言` : '留言全部加载完毕' }}

      </button>
      </div>
  </div>

</template>

<script setup lang="ts">

import { messageStore } from '@/store/messageStore';
import { userStore } from '@/store/userStore';
import { computed, onUnmounted, ref, watch } from 'vue';
import MessageBody from './MessageBody.vue';
import { selection } from '@/constants/messageSelection';


const msgs = messageStore();
const users = userStore();

let showArea = ref(selection.all);

let acceptRefresh = ref(false);

//计算剩余未加载的留言数
const remainMessage = computed(()=>{
  return msgs.messageTotal- msgs.messageList.length;
});

//加载留言
const showMoreMessage = async ()=>{
  acceptRefresh.value = true; //阻断事件防止多次触发
  if(remainMessage.value >= 1){
    await msgs.queryMessageAction(msgs.page)
  }
  acceptRefresh.value = false;
}

//登录后立即加载留言
watch(()=>users.userInfo.token,(token)=>{
    if(token){
      msgs.queryMessageAction(msgs.page)
    }
})

//卸载组件时清除评论
onUnmounted(()=>{
  msgs.cleanMessageAction();
});

//切换查看全部留言或只看当前账号发布的留言
const changeShowArea = ()=>{
  msgs.cleanMessageAction();  //先清空原来的留言
  if(showArea.value === selection.self){
    msgs.queryMessageByusernameAction(users.userInfo.username!,msgs.page);
  }
  else{
    msgs.queryMessageAction(msgs.page)
  }
}


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