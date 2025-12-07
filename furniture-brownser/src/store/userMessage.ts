import { login } from "@/api/login.api";
import type { ResultInfo } from "@/interface/ResultInfo";
import type { UserInfo } from "@/interface/UserInfo";
import { defineStore } from "pinia";


export const userMessage = defineStore('userMessage',{
  actions:{
    async login(username:string,password:string){
      const result:ResultInfo = await login(username,password);

      const data:UserInfo = result.data;

      if(data == null) throw new Error("用户名或密码错误");

      this.userMessage = data;
      this.isLogin = true;
    }
  },

  state(){
    return{
      userMessage:<UserInfo>{
        id:0,
        status:0,
        role:0,
        username:'',
        password:'',
        email:'',
        name:'',
        telephone:'',
        gender:'',
        birthday:'',
        createTime:'',
        updateTime:'',
        lastLogin:'',

      },
      isLogin:false
    }
  }

});