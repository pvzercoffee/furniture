import { addMessage } from "@/api/addMessage.api";
import { queryItem } from "@/api/queryItem.api";
import type { ItemResponse } from "@/interface/ItemResponse";
import type { MessageInfo } from "@/interface/Message";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";

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
    }
  },

  state(){
    return{
      itemList:<ItemResponse[]>[]
    }
  }
});