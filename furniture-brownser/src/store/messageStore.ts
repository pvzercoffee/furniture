import { addMessage } from "@/api/addMessage.api";
import { queryItem } from "@/api/queryItem.api";
import type { ItemResponse } from "@/interface/Message";
import type { MessageInfo, MessageResponse } from "@/interface/Message";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";
import { queryMessage } from "@/api/queryMessage.api";

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
    async queryMessageAction(page:number):Promise<MessageResponse[]>{
      try{
        const res = await queryMessage(page);
        this.messageList = Array.isArray(res) ? res : [];
        return this.messageList;
      }catch(e){
        if(e instanceof Error) toastStore().show("错误类型："+e.message)
        return [];
      }

    },
  },

  state(){
    return{
      itemList:<ItemResponse[]>[],
      messageList:<MessageResponse[]>[]
    }
  }
});