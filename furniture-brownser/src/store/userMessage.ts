import { login } from "@/api/login.api";
import { signup } from "@/api/signup.api.";
import type { ResultInfo } from "@/interface/ResultInfo";
import type { LoginResponse,SignupInfo } from "@/interface/User";
import { defineStore } from "pinia";
import { toastStore } from "./toastStore";


export const userMessage = defineStore('userMessage',{
  actions:{
    async login(username:string,password:string){
      const result:ResultInfo = await login(username,password);

      const data:LoginResponse = result.data as LoginResponse;

      if(data == null) throw new Error("用户名或密码错误");


      this.userInfo = data;
      console.log(this.userInfo);
      this.isLogin = true;
    },
    async signup(signupInfo:SignupInfo){
      const result:ResultInfo = await signup(signupInfo);
      toastStore().show(result.msg);
    }
  },

  state(){
    return{
      userInfo:<LoginResponse>{
        id:0,
        username:'',
        password:'',
        email:'',
        name:'',
        telephone:'',
        gender:'',
        birthday:'',
        token:''

      },
      isLogin:false
    }
  }

});