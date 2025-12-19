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
            {{ users.userInfo.username }}
          </p>
        </div>
        <div class="base-info" >

          <div class="info-list" >
            <p class="base-info-item">用户：</p>
            <input type="text" class="info-input" :placeholder="usernameLimit.msg" v-model="showData.username"/>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">姓名：</p>
            <input type="text" class="info-input" :placeholder="nameLimit.msg" v-model="showData.name">
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">性别：</p>
            <select v-model.number="showData.gender" class="info-input">
              <option :value="1">男</option>
              <option :value="0">女</option>
            </select>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">邮箱：</p>
            <input type="text" class="info-input" :placeholder="emailLimit.msg" v-model="showData.email">
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">手机：</p>
            <input type="text" class="info-input" :placeholder="telephoneLimit.msg" v-model="showData.telephone">
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

import {  computed, reactive, ref, toRaw, watch } from 'vue';
import { useRouter } from 'vue-router';
import { userStore } from '@/store/userStore';
import '@/styles/userCenter.css'
import { toastStore } from '@/store/toastStore';
import userVerify from '@/utils/userVerify';
import type { LoginResponse } from '@/interface/User';

const users = userStore();

const {usernameLimit,emailLimit,nameLimit,telephoneLimit} = userVerify.LIMIT;

//得到一个用户数据副本
let showData:LoginResponse = reactive({...users.userInfo})

//退出登录
const exit = ()=>{
  users.exit();
  toastStore().show('已退出登录');
}
//修改个人信息
const modifyInfo = async ()=>{

  const {username,name,email,telephone} = showData;
  const {isUsernameValid,isNameValid,isEmailValid,isTelephoneValid} = userVerify;

  if(!isUsernameValid(username!)){
    toastStore().show("用户名"+usernameLimit.msg);
    return;
  }
  if(!isNameValid(name!)){
    toastStore().show("姓名"+nameLimit.msg);
    return;
  }
  if(!isEmailValid(email!)){
    toastStore().show(emailLimit.msg);
    return;
  }
  if(!isTelephoneValid(telephone!)){
    toastStore().show(telephoneLimit.msg);
    return;
  }

  const result = await users.modifyInfoAction(showData);
  if(result)
  {
    Object.assign(users.userInfo,showData);
  }
}

const isEditMode = computed(() => {
  // 1. 定义表单里实际存在的字段
  const fields = ['username', 'name', 'gender', 'email', 'telephone', 'birthday'] as const;

  // 2. 只要有一个字段对不上，就认为被修改了
  // .some 会在找到第一个差异时立即停止，性能更高
  return fields.some(key => {
    // 强制转为字符串比对，抹平 1 vs "1" 或者 null vs "" 的所有差异
    const original = String(users.userInfo[key] ?? '');
    const current = String(showData[key] ?? '');

    return original !== current;
  });
});

const router = useRouter();

// 监听登录状态，若未登录则跳到登录页
watch(()=>users.isLogin,(isLogin)=>{
  if(!isLogin){
    router.replace({
      name:'login'
    });
  }
},{immediate:true});// 首次加载也要判断
</script>
