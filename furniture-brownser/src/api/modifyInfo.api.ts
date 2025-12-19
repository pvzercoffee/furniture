import { API_URL } from "@/constants/ApiConfig";
import type { MessageResponse } from "@/interface/Message";
import type { LoginResponse } from "@/interface/User";
import { toastStore } from "@/store/toastStore";
import { userStore } from "@/store/userStore";
import axios from "axios";

interface RequestInfo {
  info:LoginResponse,
  token:string
}

export const modifyInfo = async (request:RequestInfo)=>{
  try{
    const result = await axios.put(`${API_URL}/api/user`,request.info,{
      headers:{
        token:request.token
      }
    });

    toastStore().show(result.data.msg);
    return true;

  }catch(e){
    if(e instanceof Error){
      toastStore().show("状态异常:"+e.message);
    }
  }
  return false

}