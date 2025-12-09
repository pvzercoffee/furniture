import { queryItem } from "@/api/queryItem.api";
import type { ItemResponse } from "@/interface/ItemResponse";
import { defineStore } from "pinia";

export const messageStore = defineStore('useMessageStore',{

  actions:{
    async queryItem(){
      this.itemList = await queryItem();
      console.log(this.itemList);
    }
  },

  state(){
    return{
      itemList:<ItemResponse[]>[]
    }
  }
});