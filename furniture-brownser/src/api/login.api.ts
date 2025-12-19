import { API_URL } from "@/constants/ApiConfig";
import type { ResultInfo } from "@/interface/ResultInfo";
import axios from "axios";

interface RequestInfo {
  username:string,
  password:string
}

export async function login(requst:RequestInfo):Promise<ResultInfo>{
  const result = await axios.post(API_URL+'/api/login',{
    username:requst.username,
    password:requst.password
  })

  if(result.status != 200) throw new Error('出现异常！错误码：'+result.status);
  if(result.data.code != 1) throw new Error(result.data.msg);

  if(result.data.data == null) throw new Error("用户名或密码输入错误");


  return result.data;
}