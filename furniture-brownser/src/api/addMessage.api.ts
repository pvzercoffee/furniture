import { API_URL } from "@/constants/ApiConfig";
import type { MessageInfo } from "@/interface/Message";
import { userStore } from "@/store/userStore";
import axios from "axios";

interface RequestInfo{
  message:MessageInfo,
  token:string
}

export const addMessage = async (request:RequestInfo)=>{
  const result = await axios.post(API_URL+'/api/message',request.message,{
    headers:{
      token:request.token
    }
  });


  if(result.status != 200) throw new Error("状态异常："+result.statusText);
  if(result.data.code != 1) throw new Error(result.data.msg);

  return result.data.data;
}