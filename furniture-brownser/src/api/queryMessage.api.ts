import type { MessageResponse } from "@/interface/Message";
import { userStore } from "@/store/userStore";
import axios from "axios";

const pageSize = 10;

export const queryMessage = async (page:number)=>{
  const result = await axios.get(`/api/message?&page=${page}&pageSize=${pageSize}`,{
    headers:{
      token:userStore().userInfo.token
    }
  });

  if(result.status != 200) throw new Error("状态异常："+result.statusText);
  if(result.data.code != 1) throw new Error("服务器返回错误："+result.data.data);

  return result.data.data;
}