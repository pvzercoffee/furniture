<template>
    <div class="page-login">
      <Carousel />
        <h1 class="path">首页>>登录/注册</h1>
        <h2 class="title-big">登录/注册</h2>
        <hr class="color-hr">
        <div class="login-frame">
            <form class="login">

              <div class="column">
                <span class="font-text">用户名：</span>
                <input type="text" name="username" pattern="[A-Za-z0-9]" class="form-input"  autocomplete="username"
                :maxlength="nameMaxLength" :minlength="nameMinLength"
                v-model="username"
                :placeholder="`只能输入字母或数字，${nameMinLength}-${nameMaxLength}个`"/>
              </div>

              <div class="column">
                <span class="font-text">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
                <input type="password" name="password" pattern="[A-Za-z0-9]"class="form-input"  autocomplete="current-password"
                v-model="password"
                :maxlength="pwdMaxLength" :minlength="pwdMinLength" id="input_password"
                :placeholder="`密码长度${pwdMinLength}-${pwdMaxLength}位`"/>
              </div>

              <div class="column">
                <p class="form_hint" :style="{'color':hintColor}">&nbsp;{{ hint }}</p>
              </div>

              <div class="column">
                <button type="button" class="btn-yellow" @click="login" id="btn_login">登录</button>
                <button type="button" class="btn-yellow" @click="signup" id="btn_register">注册</button>
              </div>

            </form>
        </div>
      </div>
</template>
<script setup lang="ts">

import Carousel from '@/components/Carousel.vue';
import { HintColors } from '@/constants/HintColors';
import { ref, toRef, watch } from 'vue';
import '@/styles/loginAndSignup.css'
import { useRouter } from 'vue-router';
import { userStore } from '@/store/userStore';
import { toastStore } from '@/store/toastStore';
import { storeToRefs } from 'pinia';

const InputLimit = {
  nameMaxLength : 16,
  nameMinLength : 4,
  pwdMaxLength : 12,
  pwdMinLength : 6
}

const {nameMaxLength,nameMinLength,pwdMaxLength,pwdMinLength} = InputLimit;

let username = ref('');
let password = ref('');
let hint = ref('');
let hintColor = ref(HintColors.normal)
const useToastStore = toastStore();

const user = userStore();
const userData = storeToRefs(user);

//登录函数
async function login(){
  if(!inputVerify()) return;

  //把用户名密码传给store去提交
  await user.loginAction(username.value,password.value);
  const {username:name} = user.userInfo;
  useToastStore.show("登录成功！欢迎回来，"+name);

}

//输入验证函数
function inputVerify():boolean{

  const nameLength = username.value.length;
  const pwdLength = password.value.length;

  let isVerify = false;

  if(nameLength < nameMinLength) hint.value = '用户名过短';
  else if(nameLength > nameMaxLength) hint.value = '用户名过长';
  else if(pwdLength < pwdMinLength) hint.value = '密码过短';
  else if(pwdLength > pwdMaxLength) hint.value = '密码过长';
  else
  {
    isVerify = true;
    hint.value = '输入合法';
  }

  hintColor.value = isVerify ? HintColors.legal : HintColors.illegal;

  return isVerify;
}
const router = useRouter();

//注册按钮跳转到注册页
function signup(){
  router.replace({
    name:'signup'
  });
}

//监听登录状态，若已登录则跳到个人中心
watch(userData.isLogin,()=>{
  if(userData.isLogin.value){
    router.replace({
      name:'user'
    });
  }
},
{
  //首次加载也要判断
  immediate:true
}
);

</script>

<style scoped>
.login-frame{
  background-image: url('@/images/登录.jpg');
}

#btn_register{
  margin-left: 25px;
}
</style>

