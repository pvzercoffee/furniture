import { storeToRefs } from "pinia";
import { watch } from "vue";
import { userMessage } from "@/store/userStore";

export function logined(fn:any){
  const loginInfo = storeToRefs(userMessage())

  watch(loginInfo.userInfo,()=>{
    fn(userMessage());
  });
}