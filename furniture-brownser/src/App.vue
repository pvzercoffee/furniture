<template>

  <MainHeader class="header"/>
  <MainNav class="nav"/>

  <div class="main-content">

    <Toast class="toast"/>
    <RouterView class="main-content"></RouterView>
  </div>
  <MainFooter />
</template>

<script setup lang="ts">

import MainHeader from './components/MainHeader.vue';
import Toast from './components/Toast.vue'
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import {RouterView} from 'vue-router'
import {onMounted} from 'vue'
import { userStore } from './store/userStore';

onMounted(()=>{
  userStore().selectInfo();
});

</script>

<style scoped>
@import url('@/styles/global.css');

</style>
