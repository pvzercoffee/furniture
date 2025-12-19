import { API_URL } from "@/constants/ApiConfig";
import { toastStore } from "@/store/toastStore";
import { userStore } from "@/store/userStore";
import axios from "axios";

interface RequestInfo{
  message_id:number,
  token:string
}

export const deleteMessage = async (request:RequestInfo)=>{
  try{
    const result = await axios.delete(`${API_URL}/api/message/${request.message_id}`,{
      headers:{
        token:request.token
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