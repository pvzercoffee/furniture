<template>
  <div class="page-user">
      <h1 class="path">首页>>个人中心</h1>
      <h2 class="title-big">个人中心</h2>
      <hr class="color-hr">

      <div class="login-frame">

      <div class="user">
        <div class="head">
          <div class="avatar"></div>
          <p class="avatar-text">
            {{ userData.userInfo.value.username }}
          </p>
        </div>
        <div class="base-info" >

          <div class="info-list" >
            <p class="base-info-item">用户：</p>
            <input type="text" class="info-input"  v-model="showData.username"/>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">姓名：</p>
            <input type="text" class="info-input"  v-model="showData.name">
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">性别：</p>
            <select v-model.number="showData.gender" class="info-input">
              <option value="1">男</option>
              <option value="0">女</option>
            </select>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">邮箱：</p>
            <input type="text" class="info-input" v-model="showData.email">
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">手机：</p>
            <input type="text" class="info-input" v-model="showData.telephone">
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">生日：</p>
            <input type="date" v-model="showData.birthday" class="info-input"/>
          </div>

          <hr class="item-split" />
          <button type="button" class="click-text" v-show="isEditMode" @click="modifyInfo">修改信息</button>
          <button type="button" class="click-text" @click="exit">退出登录</button>

        </div>
      </div>
    </div>
  </div>

</template>

<script setup lang="ts">

import {  ref, watch } from 'vue';
// import '@/styles/loginAndSignup.css'
import { useRouter } from 'vue-router';
import { userStore } from '@/store/userStore';
import { storeToRefs } from 'pinia';
import '@/styles/userCenter.css'
import { toastStore } from '@/store/toastStore';
import type { LoginResponse } from '@/interface/User';
import userVerify from '@/utils/userVerify';
import Carousel from '@/components/Carousel.vue';

const user = userStore();
const userData = storeToRefs(user);

//得到一个用户数据副本
const showData = ref<LoginResponse>(JSON.parse(JSON.stringify(userData.userInfo.value)));

//退出登录
const exit = ()=>{
  user.exit();
  toastStore().show('已退出登录');
}

//修改个人信息
let isEditMode = ref(false);
const modifyInfo = async ()=>{

  const {username,name,email,telephone} = showData.value;
  const {isUsernameValid,isNameValid,isEmailValid,isTelephoneValid} = userVerify;

  if(!isUsernameValid(username!)){
    toastStore().show("用户名字数不规范");
    return;
  }
  if(!isNameValid(name!)){
    toastStore().show("姓名字数不规范");
    return;
  }
  if(!isEmailValid(email!)){
    toastStore().show("邮箱字数不规范");
    return;
  }
  if(!isTelephoneValid(telephone!)){
    toastStore().show("电话号码字数不规范");
    return;
  }

  const result = await user.modifyInfoAction(showData.value);
  if(result) Object.assign(user.userInfo,showData.value);
}

//如果用户修改了信息则显示提交修改按钮
watch(showData,()=>{
  if(JSON.stringify(showData.value) === JSON.stringify(userData.userInfo.value)){
    isEditMode.value = false;
  }
  else{
    isEditMode.value = true
  }

},{deep:true})


const router = useRouter();

// 监听登录状态，若未登录则跳到登录页
watch(userData.isLogin,()=>{
  if(!userData.isLogin.value){
    router.replace({
      name:'login'
    });
  }
},{
  // 首次加载也要判断
  immediate:true
}
);
</script>
