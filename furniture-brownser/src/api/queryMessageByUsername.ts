import { API_URL } from "@/constants/ApiConfig";
import type { MessageResponse } from "@/interface/Message";
import { messageStore } from "@/store/messageStore";
import { userStore } from "@/store/userStore";
import axios from "axios";

interface RequestInfo{
  username:string,
  index:number,
  pageSize:number,
  token:string
}

export const queryMessageByUsername = async (request:RequestInfo)=>{

  const result = await axios.get(`${API_URL}/api/message/${request.username}`,{
    params:{
      index:request.index,
      pageSize:request.pageSize
    },
    headers:{
      token:request.token
    }
  });

  if(result.status != 200) throw new Error("状态异常："+result.statusText);
  if(result.data.code != 1) throw new Error("服务器返回错误："+result.data.data);

  return result.data.data;
}