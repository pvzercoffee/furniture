<template>
<div class="main-header">
    <header>
        <span class="title-small">{{ title }}</span>
        <!-- <div id="header-message"> -->
            <div class="icon icon_telephone header-icon">
                <span class="text-small">400-200-500</span>
            </div>
            <div class="icon icon_email header-icon">
                <span class="text-small">eigewuif@gmail.com</span>
            </div>
        <!-- </div> -->
    </header>
    <div class="empty">

    </div>
</div>
</template>

<script lang="ts" setup>
import { logined } from '@/utils/logined';
import { ref } from 'vue';
let title = ref('欢迎来到精美家具');


logined((loginInfo:any)=>{
  if(loginInfo.isLogin){
    const {name,gender} = loginInfo.userInfo;
    title.value = name + (gender == 1 ? '先生' : '女士') + '，欢迎来到精美家居';
  }
  else{
    title.value = '欢迎来到精美家居';
  }
});


</script>

<style scoped>
.main-header{
    line-height: 30px;
    height: 30px;
    z-index: 999;
    overflow: hidden;
    width: 100%;
    background-color: aqua;
}
header{
    width:100%;
    line-height: 30px;
    /* margin-bottom: 70px; */
    background-color: #000;
    color: #fff;
    font-size: 15px;
    line-height: 35px;;
    display: flex;
}

header>.icon{
    height:35px;
    background-repeat: no-repeat;
    padding-left: 25px;
    background-image: url('@/images/icon.jpg');
    margin-left: auto;
}
.icon_telephone{
    margin-left: auto;
    background-position: 0 -32px;
}
.icon_email{
    background-position: 0 -73px;
}
@media(max-width:768px){
  .main-header{
    position: fixed;
    left: 0;
    top: 0;
  }
}
</style>