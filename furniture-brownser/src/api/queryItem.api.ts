import type { ResultInfo } from "@/interface/ResultInfo";
import axios from "axios";

export async function queryItem() {
  const result = await axios.get('/api/message/queryItem') ;
  if(result.status != 200) new Error("网络请求错误，无法获取项目列表");
  if(result.data.code != 1) new Error(result.data.msg);

  return result.data.data;
}