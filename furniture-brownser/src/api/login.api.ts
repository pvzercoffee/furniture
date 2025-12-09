import type { ResultInfo } from "@/interface/ResultInfo";
import axios from "axios";

export async function login(username:string,password:string):Promise<ResultInfo>{
  const result = await axios.post('/api/login',{
    username:username,
    password:password
  })

  if(result.status != 200) throw new Error('出现异常！错误码：'+result.status);
  return result.data;
}