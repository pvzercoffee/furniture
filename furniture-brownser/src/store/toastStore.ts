import { defineStore } from "pinia";

export const toastStore =  defineStore('toastStore',{
  actions:{
    show(value:string){
      this.text = value;
      this.isShow = true;
      const start = setInterval(()=>{
        this.isShow = false;
        clearInterval(start);
      },this.time);
    },
  },

  state(){
    return{
      text:'',
      isShow:false,
      time:2600
    }
  }

});