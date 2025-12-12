import { addMessage } from "@/api/addMessage.api";
import { queryItem } from "@/api/queryItem.api";
import type { ItemResponse } from "@/interface/Message";
import type { MessageInfo, MessageResponse } from "@/interface/Message";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";
import { queryMessage } from "@/api/queryMessage.api";
import { getPassTime } from "@/utils/getPassTime";

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
    async queryMessageAction(page:number){
      try{
        const res = await queryMessage(page);
        const {messages,total} = res;

        //转换日期
        messages.forEach((element:MessageResponse,index:number)=>{
          if(messages[index]?.createTime){
            messages[index]!.createTime = getPassTime(new Date(element.createTime)) || element.createTime
          }
        });

        this.messageTotal = total;
        this.messageList.push(...messages);


        return this.messageList;
      }catch(e){
        if(e instanceof Error && e.message) toastStore().show('登录后才能留言'+e.message)
        return [];
      }
    },
  },

  state(){
    return{
      itemList:<ItemResponse[]>[],
      messageList:<MessageResponse[]>[],
      messageTotal:0
    }
  }
});