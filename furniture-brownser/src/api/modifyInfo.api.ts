import type { MessageResponse } from "@/interface/Message";
import type { LoginResponse } from "@/interface/User";
import { toastStore } from "@/store/toastStore";
import { userStore } from "@/store/userStore";
import axios from "axios";


export const modifyInfo = async (info:LoginResponse)=>{
  try{
    const result = await axios.put(`/api/user`,info,{
      headers:{
        token:userStore().userInfo.token
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