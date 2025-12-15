import { API_URL } from "@/constants/ApiConfig";
import type { ResultInfo } from "@/interface/ResultInfo";
import axios from "axios";

export async function login(username:string,password:string):Promise<ResultInfo>{
  const result = await axios.post(API_URL+'/api/login',{
    username:username,
    password:password
  })

  if(result.status != 200) throw new Error('出现异常！错误码：'+result.status);
  if(result.data.code != 1) throw new Error(result.data.msg);

  if(result.data.data == null) throw new Error("用户名或密码输入错误");


  return result.data;
}