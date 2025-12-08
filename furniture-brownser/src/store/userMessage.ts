import { login } from "@/api/login.api";
import type { LoginInfo } from "@/interface/LoginInfo";
import type { ResultInfo } from "@/interface/ResultInfo";
import type { UserInfo } from "@/interface/UserInfo";
import { defineStore } from "pinia";


export const userMessage = defineStore('userMessage',{
  actions:{
    async login(username:string,password:string){
      const result:ResultInfo = await login(username,password);

      const data:LoginInfo = result.data as LoginInfo;

      if(data == null) throw new Error("用户名或密码错误");

      console.log(data);
      this.userMessage = data;
      this.isLogin = true;
    }
  },

  state(){
    return{
      userMessage:<LoginInfo>{
        id:0,
        username:'',
        password:'',
        email:'',
        name:'',
        telephone:'',
        gender:'',
        birthday:'',

      },
      isLogin:false
    }
  }

});