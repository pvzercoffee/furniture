import { API_URL } from "@/constants/ApiConfig";
import { toastStore } from "@/store/toastStore";
import { userStore } from "@/store/userStore";
import axios from "axios";


export const deleteMessage = async (message_id:number)=>{
  try{
    const result = await axios.delete(`${API_URL}/api/message/${message_id}`,{
      headers:{
        token:userStore().userInfo.token
      }
    });

    toastStore().show(result.data.msg);
    return true;

  }catch(e){
    if(e instanceof Error){
      toastStore().show("状态异常:"+e.message);
      return false;
    }
  }
  return false

}