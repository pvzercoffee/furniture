import { login } from "@/api/login.api";
import { signup } from "@/api/signup.api.";
import type { ResultInfo } from "@/interface/ResultInfo";
import type { LoginResponse,SignupInfo } from "@/interface/User";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";


export const userMessage = defineStore('userMessage',{
  actions:{
    //登录业务逻辑
    async login(username:string,password:string){
      const result:ResultInfo = await login(username,password);

      const data:LoginResponse = result.data as LoginResponse;

      if(data == null) throw new Error("用户名或密码错误");

      this.userInfo = data;
      this.isLogin = true;
    },

    //注册业务逻辑
    async signup(signupInfo:SignupInfo){
      const result:ResultInfo = await signup(signupInfo);
      toastStore().show(result.msg);
    },

    //退出登录业务逻辑
    exit(){

      //清空数据，返还登录状态
      this.userInfo = {
        id:-1,
        username:'',
        email:'',
        name:'',
        telephone:'',
        gender:2,
        birthday:'',
        token:''
      }
      this.isLogin = false;
    }
  },

  state(){
    return{
      userInfo:<LoginResponse>{
        id:-1,
        username:'',
        email:'',
        name:'',
        telephone:'',
        gender:2,
        birthday:'',
        token:''

      },
      isLogin:false
    }
  }

});