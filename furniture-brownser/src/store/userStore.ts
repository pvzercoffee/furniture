import { login } from "@/api/login.api";
import { signup } from "@/api/signup.api.";
import type { ResultInfo } from "@/interface/ResultInfo";
import type {  LoginResponse,SignupInfo } from "@/interface/User";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";
import { me } from "@/api/me.api";
import { modifyInfo } from "@/api/modifyInfo.api";


export const userStore = defineStore('userStore',{
  actions:{
    //登录业务逻辑
    async loginAction(username:string,password:string){

      try{
        const result:ResultInfo = await login(username,password);
        const data:LoginResponse = result.data as LoginResponse;

        this.userInfo = data;
        this.isLogin = true;

        localStorage.setItem("token",this.userInfo.token as string);

      }catch(e){
        if(e instanceof Error){
          toastStore().show(e.message);
          throw e;
        }
      }
    },

    //注册业务逻辑
    async signupAction(signupInfo:SignupInfo){
      const result:ResultInfo = await signup(signupInfo);
      return result;
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
      localStorage.removeItem("token");
    },

    //根据token查询用户信息业务逻辑
    async selectInfo(){
      const token = localStorage.getItem("token");
      if(token == null) return;
      if(token == '' || token == ' ') return;
      if(token.length < 10) return;

      const result = await me(token);
      if(result == null) return;
      this.userInfo = result;
      this.userInfo.token = token;
      this.isLogin = true;
    },

    //修改用户信息逻辑
    async modifyInfoAction(info:LoginResponse){
      return await modifyInfo(info);
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
  },


});