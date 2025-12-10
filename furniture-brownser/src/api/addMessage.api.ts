import type { MessageInfo } from "@/interface/Message";
import { userMessage } from "@/store/userStore";
import axios from "axios";

export const addMessage = async (message:MessageInfo)=>{
  console.log(userMessage().userInfo.token);
  const result = await axios.post('/api/message',message,{
    headers:{
      token:userMessage().userInfo.token
    }
  });


  if(result.status != 200) throw new Error("状态异常："+result.statusText);
  if(result.data.code != 1) throw new Error(result.data.msg);

  return result.data.data;
}