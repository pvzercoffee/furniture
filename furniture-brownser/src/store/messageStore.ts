import { addMessage } from "@/api/addMessage.api";
import { queryItem } from "@/api/queryItem.api";
import type { ItemResponse } from "@/interface/Message";
import type { MessageInfo, MessageResponse } from "@/interface/Message";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";
import { queryMessage } from "@/api/queryMessage.api";
import { getPassTime } from "@/utils/getPassTime";
import { deleteMessage } from "@/api/deleteMessage.api";
import { queryMessageByUsername } from "@/api/queryMessageByUsername";
import { userStore } from "./userStore";

//获取评论后解析与封装到state
const pushMessage = (res:any)=>{
  try{

      const {messages,total}:{messages:MessageResponse[],total:number} = res;
      const msgs = messageStore();

      //转换日期
      messages.forEach((element:MessageResponse,index:number)=>{
        if(messages[index]?.createTime){
          messages[index]!.createTime = getPassTime(new Date(element.createTime)) || element.createTime
        }
      });

      msgs.messageTotal = total;
      //把新留言合并到旧的留言、去重
      msgs.messageList = [...new Map([...msgs.messageList,...messages].map(v=>[v.id,v])).values()]

      return msgs.messageList;
  }catch(e){
    if(e instanceof Error && e.message) toastStore().show('登录后才能留言'+e.message)
    return [];
  }
}


export const messageStore = defineStore('useMessageStore',{

  actions:{
    //查询留言可用项目
    async queryItemAction(){
      this.itemList = await queryItem();
    },

    //添加留言
    async addMessageAction(message:MessageInfo){
      try{
        return await addMessage(message);
      }
      catch(e){
        if(e instanceof Error){
          toastStore().show("出现异常 "+e.name);
          return;
        }
      }
    },

    //查询留言
    async queryMessageAction(){
      const {token} = userStore().userInfo;
      const res = await queryMessage({
        index:this.index,
        pageSize:this.pageSize,
        token:token!
      });
      pushMessage(res);
      this.index+=this.pageSize;

    },

    //根据id查询留言
    async queryMessageByusernameAction(){
      const {username,token} = userStore().userInfo;
      const res = await queryMessageByUsername({
        username:username!,
        index:this.index,
        pageSize:this.pageSize,
        token:token!
      });
      pushMessage(res);
      this.index+=this.pageSize;

    },

    //删除留言
    async deleteMessageAction(message_id:number){
      const result = await deleteMessage(message_id);

      if(result){
        this.messageList = this.messageList.filter(v => v.id !== message_id);
        this.messageTotal--;

        this.index--;
      }

      return result;
    },

    //清空留言
    cleanMessageAction(){
      this.messageTotal = 0;
      this.index = 0;
      this.messageList = [];
    }
  },

  state(){
    return{
      itemList:<ItemResponse[]>[],
      messageList:<MessageResponse[]>[],
      messageTotal:0,
      index:0,
      pageSize : 10,
    }
  }
});