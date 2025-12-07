import type { ResultInfo } from "@/interface/ResultInfo";
import axios from "axios";

export async function login(username:string,password:string):Promise<ResultInfo>{
  const result = await axios.post('/api/login',{
    username:username,
    password:password
  })

  return result.data;
}