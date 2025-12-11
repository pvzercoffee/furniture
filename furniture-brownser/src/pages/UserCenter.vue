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
        <div class="base-info">

          <div class="info-list">
            <p class="base-info-item">用户名：{{ userData.userInfo.value.username }}</p>
            <button type="button" @click="modifyInfo" class="base-button">修改</button>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">姓名：{{ userData.userInfo.value.name }}</p>
            <button type="button" @click="modifyInfo" class="base-button">修改</button>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">性别：{{ (userData.userInfo.value.gender == 1 ? '男' : '女') }}</p>
            <button type="button" @click="modifyInfo" class="base-button">修改</button>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">邮箱：{{ userData.userInfo.value.email }}</p>
            <button type="button" @click="modifyInfo" class="base-button">修改</button>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">手机：{{ userData.userInfo.value.telephone }}</p>
            <button type="button" @click="modifyInfo" class="base-button">修改</button>
          </div>
          <hr class="item-split" />
          <div class="info-list">
            <p class="base-info-item">生日：{{ userData.userInfo.value.birthday }}</p>
            <button type="button" @click="modifyInfo" class="base-button">修改</button>
          </div>

          <hr class="item-split" />

          <div class="bottom">
            <button type="button" class="click-text" @click="modifyInfo">修改信息</button>
            <button type="button" class="click-text" @click="exit">退出登录</button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script setup lang="ts">

import { toRefs, watch } from 'vue';
// import '@/styles/loginAndSignup.css'
import { useRouter } from 'vue-router';
import { userStore } from '@/store/userStore';
import { storeToRefs } from 'pinia';
import { toastStore } from '@/store/toastStore';
import ModifyInfo from '@/components/ModifyInfo.vue';

const user = userStore();
const userData = storeToRefs(user);

//退出登录
const exit = ()=>{
  user.exit();
  toastStore().show('已退出登录');
}

//修改用户信息
const modifyInfo = ()=>{

}

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
<style scoped>

.login-frame{
  background-image: url('@/images/注册.jpg');

}
.base-button{
  width: 90px;
  height: 30px;
  margin-top: 12px;
  margin-left: auto;
  border: 0;
}
.base-button:hover{
  background-color: #ccc;
}
.head{
  display: flex;
  flex-direction: column;
  background-color: rgb(253, 235, 216);
  width: 300px;
  margin: auto;
  padding: 50px 83px 20px 83px;
  border-radius: 15px 15px 0 0;

}
.avatar{
  width: 150px;
  height: 150px;
  border-radius: 50%;
  margin: auto;
  background-image: url('@/images/canjv.jpg');
  background-repeat: no-repeat;
  background-size: 100% auto;
  background-position: center;

}
.click-text{
  font-size: 14px;
  margin-left: 0px;
  text-decoration: underline;
  color: chocolate;
  border: 0;
  flex: 1;
  background-color: #ffffff00;
}

.avatar-text{
  margin: auto;
  margin-top: 15px;
  font-size: 18px;

}
.info-list{
  display: flex;
  flex-wrap: wrap;
}
.base-info{
  margin: auto;
  background-color: #fff;
  width: 416px;
  line-height: 55px;
  /* margin-top: 30px; */
  padding: 25px;
  border-radius: 0 0 15px 15px;

}
.base-info-item{

  margin: 0;
  padding: 0 20px 0 20px;
}
.user-frame{
  display: flex;
  flex-direction:column;
  margin: auto;
  width: 100%;
}
.user{
  display: flex;
  flex-direction: column;
  margin:30px;
}
.bottom{
  display: flex;
  width: 100%;
  margin-top: 30px;
}

@media(max-width:768px){
  .head{
    max-width: 300px;
    padding: 20px;
  }
  .base-info{
    max-width: 300px;
    padding: 20px;
  }
}
</style>