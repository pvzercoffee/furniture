<template>
  <div class="root">
    <nav class="navbar">
      <div class="logo" ></div>
      <!-- 移动端汉堡按钮 -->
      <button
        class="mobile-menu-btn"
        @click="toggleMobileMenu"
        aria-label="菜单"
      >
        <!-- 三横杠图标 -->
        <div class="hamburger" :class="{ 'active': isMobileMenuOpen }">
          <span></span>
          <span></span>
          <span></span>
        </div>
      </button>

      <!-- 桌面端横向导航 -->
      <div class="desktop-nav">
        <ul class="nav-list">
          <li class="nav-item" v-for="item in navItems" :key="item.id">
            <RouterLink
                  replace
                  :to="{path:item.path}"
                  @click="closeMobileMenu"
                  class="nav-link">
                  {{item.name}}
                </RouterLink>
          </li>
        </ul>
      </div>

      <!-- 移动端侧边菜单 -->
      <transition name="slide">
        <div
          v-if="isMobileMenuOpen"
          class="mobile-menu-overlay"
          @click="closeMobileMenu"
        >
          <div class="mobile-sidebar" @click.stop>
            <div class="mobile-header">
              <button class="close-btn" @click="closeMobileMenu">
                <span>&times;</span>
              </button>
            </div>
            <ul class="mobile-nav-list">
              <li
                class="mobile-nav-item"
                v-for="item in navItems"
                :key="item.id"
              >
                <RouterLink
                  replace
                  :to="{path:item.path}"
                  @click="closeMobileMenu"
                  class="mobile-nav-link">
                  {{item.name}}
                </RouterLink>
              </li>
            </ul>
          </div>
        </div>
      </transition>
    </nav>
    <div class="empty"></div>
  </div>
</template>

<script lang="ts" setup>
import { userStore } from '@/store/userStore';
import { ref, onMounted, onUnmounted, watch } from 'vue'

const isMobileMenuOpen = ref(false)
const isMobileView = ref(false)

// 导航项数据
const navItems = ref([
  { id: 1, name: '首页', path: '/home' },
  { id: 2, name: '产品展示', path: '/show' },
  { id: 3, name: '关于我们', path: '/about' },
  { id: 4, name: '在线留言', path: '/board' },
  { id: 5, name: '登录注册', path: '/login' }
])

const user = userStore();
watch(()=>user.isLogin,()=>{
  if(user.isLogin){
    navItems.value[4]!.name = "个人中心";
  }
  else{
    navItems.value[4]!.name = "登录注册"
  }
});

// 检查视口宽度
const checkViewport = () => {
  isMobileView.value = window.innerWidth <= 768
  // 如果是桌面端，确保移动菜单关闭
  if (!isMobileView.value) {
    isMobileMenuOpen.value = false
  }
}

// 切换移动菜单
const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value
}

// 关闭移动菜单
const closeMobileMenu = () => {
  isMobileMenuOpen.value = false
}

// 监听窗口大小变化
onMounted(() => {
  checkViewport()
  window.addEventListener('resize', checkViewport)
})

onUnmounted(() => {
  window.removeEventListener('resize', checkViewport)
})
</script>

<style scoped>
.navbar {

  background-color:rgb(253, 235, 216);
  padding: 0 20px;
  height: 70px;
  width: 100%;
  display: flex;
  z-index: 999;
  align-items: center;
}

/* 移动端汉堡按钮 */
.mobile-menu-btn {
  display: none;
  background: none;
  border: none;
  cursor: pointer;
  padding: 70px;
  z-index: 1000;
}

.hamburger {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 24px;
  height: 18px;
  transition: all 0.3s ease;
}

.hamburger span {
  display: block;
  width: 100%;
  height: 2px;
  background: #000;
  border-radius: 2px;
  transition: all 0.3s ease;
}

.hamburger.active span:nth-child(1) {
  transform: translateY(8px) rotate(45deg);
}

.hamburger.active span:nth-child(2) {
  opacity: 0;
}

.hamburger.active span:nth-child(3) {
  transform: translateY(-8px) rotate(-45deg);
}

/* 桌面端导航 */
.desktop-nav {
  display: block;
  margin-left: 100px;
  width: 100%;
}

.nav-list {
  display: flex;
  gap: 30px;
  list-style: none;
  margin: 0;
  padding: 0;
}

.nav-item {
  margin: 0;
}

.nav-link {
  color: #000;
  text-decoration: none;
  font-size: 16px;
  padding: 8px 12px;
  border-radius: 4px;
  transition: background 0.3s;
}

.nav-link:hover {
  background: rgb(233, 203, 172);
}

/* 移动端侧边菜单 */
.mobile-menu-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.mobile-sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 250px;
  height: 100%;
  background: #222;
  padding: 20px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.2);
  overflow-y: auto;
}

.mobile-header {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 30px;
}

.close-btn {
  background: none;
  border: none;
  color: #000;
  font-size: 24px;
  cursor: pointer;
  padding: 5px;
}

.mobile-nav-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.mobile-nav-item {
  margin-bottom: 15px;
}

.mobile-nav-link {
  color: #fff;
  text-decoration: none;
  font-size: 18px;
  display: block;
  padding: 10px;
  border-radius: 4px;
  transition: background 0.3s;
}

.mobile-nav-link:hover {
  background: rgba(255, 255, 255, 0.1);
}

/* 过渡动画 */
.slide-enter-active,
.slide-leave-active {
  transition: opacity 0.3s;
}

.slide-enter-from,
.slide-leave-to {
  opacity: 0;
}

.slide-enter-active .mobile-sidebar,
.slide-leave-active .mobile-sidebar {
  transition: transform 0.3s;
}

.slide-enter-from .mobile-sidebar,
.slide-leave-to .mobile-sidebar {
  transform: translateX(-100%);
}
.logo{
    width: 150px;
    height: 30px;
    /* margin:20px 20px; */
    background-image: url('../images/logo.png');
    background-size: 100px 100%;
    background-repeat: no-repeat;
    margin-right: auto;
}
/* 响应式设计 */
@media (max-width: 768px) {
  .mobile-menu-btn {
    display: block;
  }

  .desktop-nav {
    display: none;
  }

  .navbar {
    justify-content: flex-end;
  }
  .empty{
    height: 100px;
    width: 100%;
  }
  .navbar{
    position: fixed;
    top: 30px;
  }
}

@media (min-width: 769px) {
  .mobile-menu-overlay {
    display: none !important;
  }
}
</style>








<!-- <template>
<nav>
    <div class="logo" ></div>

    <ul class="items" >
        <li class="nav-link"><div id="menu"></div></li>
        <li><RouterLink replace :to="{name:'home'}" class="nav-link">首页</RouterLink></li>
        <li><RouterLink replace :to="{name:'show'}" class="nav-link">产品展示</RouterLink></li>
        <li><RouterLink replace :to="{name:'about'}" class="nav-link">关于我们</RouterLink></li>
        <li><RouterLink replace :to="{name:'board'}" class="nav-link">在线留言</RouterLink></li>
        <li  v-show="!userStore().isLogin"><RouterLink replace :to="{name:'login'}" class="nav-link">登录注册</RouterLink></li>
        <li v-show="userStore().isLogin" ><RouterLink replace :to="{name:'user'}" class="nav-link">个人中心</RouterLink></li>

    </ul>


</nav>
</template>

<script lang="ts" setup>
import { userStore } from '@/store/userStore';
import { ref } from 'vue';
import {RouterLink} from 'vue-router'


</script>

<style scoped>

nav{
    width:100%;
    height: 70px;
    background-color:rgb(253, 235, 216);
    overflow: hidden;
    display: flex;
}
.logo{
    width: 150px;
    height: 30px;
    margin:20px 20px;
    background-image: url('../images/logo.png');
    background-size: 100px 100%;
    background-repeat: no-repeat;
    margin-right: 20%;;
}

.items{
    display: flex;
    list-style-type: none;
    gap: 30px;
    line-height: 40px;
    height: 400px;
    background-color:rgb(253, 235, 216);
    flex: 1;

}

.nav-link:link,.footer-link{
    text-decoration: none;
    border-radius: 5px;
}
.nav-link:hover,.nav-link:active{
    /* text-decoration: underline; */
    background-color: rgb(233, 203, 172);
    /* box-shadow: 2px 2px 15px 2px #666; */
}
.nav-link{
    color: #000;
    background-color: rgb(253, 235, 216);
    padding: 10px;
}
.footer-link{
    color: #fff;
}
#menu{
  width: 40px;
  height: 40px;
  background-image: url('@/images/menu.svg');
  background-repeat: no-repeat;
  background-size: 100%;
  position: relative;
  left: 30px;
  display: none;
}
#menu:hover{
  background-color: rgb(253, 235, 216);
}
@media (max-width: 768px) {
  .items:hover{
    display: flex;
    z-index: 999;
    gap: 5px;
    /* background-color: aqua; */
  }
  nav:has(.nav-link:hover){
    /* z-index: 0; */
    overflow: visible;

  }
  #menu{
    display: flex;
  }

}
</style> -->