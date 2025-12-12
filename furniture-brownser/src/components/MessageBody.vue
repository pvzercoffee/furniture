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
import { queryMessage } from '@/api/queryMessage.api';
import { messageStore } from '@/store/messageStore';
import { userStore } from '@/store/userStore';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';


const props = defineProps(['data']);
let {data} = props;
if(!data) data = {
  id:0,
  username:'用户名',
  name:'姓名',
  telephone:'手机号',
  email:'邮箱',
  text:'正文',
  createTime:'发表时间',
  itemList:['分类列表1','分类列表2']
}

const focu = ref(false);
const deleteNumber = ref(10);

const deleteMessage = async (message_id:number)=>{
  focu.value = true;
  const result = await messageStore().deleteMessageAction(message_id);
  if(result){
    const msg = messageStore();
    if((msg.messageList.length-1)/msg.pageSize+1 < msg.page) msg.page--;
    msg.messageList = [];
    for(let i = 1;i <= msg.page;i++){
      await msg.queryMessageAction(i);
      msg.update++;
    }
  }
  focu.value = true;

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