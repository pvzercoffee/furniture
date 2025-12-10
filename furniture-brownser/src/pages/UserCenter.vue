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
                <button type="button" class="click-text" @click="exit">退出登录</button>
              </p>
            </div>
            <div class="base-info">

              <p class="base-info-item">姓名：{{ userData.userInfo.value.name }}</p>
              <hr class="item-split" />
              <p class="base-info-item">性别：{{ (userData.userInfo.value.gender == 1 ? '男' : '女') }}</p>
              <hr class="item-split" />
              <p class="base-info-item">邮箱：{{ userData.userInfo.value.email }}</p>
              <hr class="item-split" />
              <p class="base-info-item">手机：{{ userData.userInfo.value.telephone }}</p>
              <hr class="item-split" />
              <p class="base-info-item">生日：{{ userData.userInfo.value.birthday }}</p>
              <hr class="item-split" />
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

const user = userStore();
const userData = storeToRefs(user);

//退出登录
function exit(){
  user.exit();
  toastStore().show('已退出登录');
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
.head{
  display: flex;
  flex-direction: column;
  background-color: rgb(253, 235, 216);
  width: auto;
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
  background-color: #ffffff00;
}
.avatar-text{
  margin: auto;
  margin-top: 15px;
  font-size: 18px;

}
.base-info{
  margin: auto;
  background-color: #fff;
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
</style>