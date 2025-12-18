<template>

    <div class="message">
      <div class="msg-header">
        <div class="msg-avatar"></div>
        <p class="msg-username">{{ data.username }}</p>
        <button type="button" @click="deleteMessage(data.id)" :disabled="focu" v-show="data.username == userStore().userInfo.username" class="delete-button">删除</button>
      </div>

      <div class="msg-info">
        <p class="msg-time">{{ data.createTime }}</p>
      </div>

      <div class="msg-body">
        <p class="msg-text">
          {{ data.text }}
        </p>
      </div>

      <div class="msg-items">
        <div class="msg-item"  v-for="item in data.itemList" :key="item"><p class="item-name">{{ item }}</p></div>

      </div>

  </div>

</template>

<script setup lang="ts">
import { messageStore } from '@/store/messageStore';
import { toastStore } from '@/store/toastStore';
import { userStore } from '@/store/userStore';
import { ref } from 'vue';

const props = defineProps(['data']);  //外部提供留言数据
const focu = ref(false);  //删除按钮是否可用
const toast = toastStore(); //全局消息toast
const msgs = messageStore();  //留言状态store

//删除指定的留言，html传入留言id
const deleteMessage = async (message_id:number)=>{

  focu.value = true;  //禁止按钮点击。多次发起请求

  //执行删除
  const result = await msgs.deleteMessageAction(message_id);
  if(result){
    toast.show('删除成功');
  }
  else{
    toast.show('删除失败');
  }
  focu.value = true;  //恢复按钮可点击
}


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
  margin: 40px 0 40px 20px;
}
.message{
  display: flex;
  background-color: #00000009;
  border-radius: 15px;
  flex-direction: column;
  padding: 20px 5% 20px 5%;
  margin: 0 20px 20px 20px;
}
.delete-button{
  width: 80px;
  height: 30px;
  margin-top: 10px;
  border: 0;
  background-color: #eee;
  margin-left: auto;
}
.delete-button:hover{
  background-color: #aaa;
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
  font-weight: 400;

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