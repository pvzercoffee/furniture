import { API_URL } from "@/constants/ApiConfig";
import type { ResultInfo } from "@/interface/ResultInfo";
import type { SignupInfo } from "@/interface/User";
import axios from "axios";

export async function signup(msg:SignupInfo):Promise<ResultInfo>{
   console.log('异步前');
   const result = await axios.post(API_URL+'/api/signup',msg);

   console.log('异常前');
   if(result.status != 200) throw new Error('出现异常！错误码：'+result.status);
   console.log('异常后，结果：'+result.data);
   return result.data;
}