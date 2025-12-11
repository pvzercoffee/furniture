import { storeToRefs } from "pinia";
import { watch } from "vue";
import { userStore } from "@/store/userStore";

export function logined(fn:any){
  const loginInfo = storeToRefs(userStore())

  watch(loginInfo.userInfo,()=>{
    fn(userStore());
  },{
    deep:true
  });
}