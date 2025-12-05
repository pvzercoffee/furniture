import axios from "axios";
import { defineStore } from "pinia";


export const useUserMessage = defineStore('userMessage',{
  actions:{
    async login(){
      const result = axios.post('/api/login',{
        username:'test012',
        password:'3674227'
      });

      console.log((await result).data);
    }
  }
});