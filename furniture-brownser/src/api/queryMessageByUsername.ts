import { API_URL } from "@/constants/ApiConfig";
import type { MessageResponse } from "@/interface/Message";
import { messageStore } from "@/store/messageStore";
import { userStore } from "@/store/userStore";
import axios from "axios";


export const queryMessage = async (username:string,page:number)=>{
  const result = await axios.get(`${API_URL}/api/message/${username}/?&page=${page}&pageSize=${messageStore().pageSize}`,{
    headers:{
      token:userStore().userInfo.token
    }
  });

  if(result.status != 200) throw new Error("状态异常："+result.statusText);
  if(result.data.code != 1) throw new Error("服务器返回错误："+result.data.data);

  return result.data.data;
}