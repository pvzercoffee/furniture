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
        const mSet = new Set<number>();

        this.messageList.push(...res.messages)
        this.messageTotal = res.total;

        //去除重复结果

        for(let index = 0;index < this.messageList.length;index++){

          let element = this.messageList[index]!;

          if(mSet.has(element.id)){
            if(this.messageList[index]?.itemList){
              this.messageList[index-1]?.itemList.push(...this.messageList?.[index]?.itemList ?? []);
            }
            this.messageList.splice(index-1,1);
          }
          mSet.add(element.id);



        }

        //转换日期
        this.messageList.forEach((element,index)=>{
          if(this.messageList[index]?.createTime){
            this.messageList[index]!.createTime = getPassTime(new Date(element.createTime)) || element.createTime
          }
        });


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