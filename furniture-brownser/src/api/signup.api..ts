import type { ResultInfo } from "@/interface/ResultInfo";
import type { SignupInfo } from "@/interface/User";
import axios from "axios";

export async function signup(msg:SignupInfo):Promise<ResultInfo>{
   const result = await axios.post('/api/signup',msg);

   if(result.status != 200) throw new Error('出现异常！错误码：'+result.status);
   return result.data;
}