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
import '@/styles/mainNav.css'

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
