import { API_URL } from "@/constants/ApiConfig";
import type { LoginInfo, LoginResponse } from "@/interface/User";
import axios from "axios";

export async function me(token:string):Promise<LoginResponse>{
  const result = await axios.post(API_URL+'/api/me',{},{
    headers:{
      token:token
    }
  });
  if(result.status != 200) throw new Error('出现异常！错误码：'+result.status);
  if(result.data == null) throw new Error("登录信息已失效，请重新登录");

  return result.data.data;


}