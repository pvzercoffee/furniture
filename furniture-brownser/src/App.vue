<script setup lang="ts">
import MainHeader from './components/MainHeader.vue';
import Toast from './components/Toast.vue'
import MainNav from './components/MainNav.vue';
import MainFooter from './components/MainFooter.vue';
import {RouterView} from 'vue-router'


</script>

<template>

  <MainHeader />
  <MainNav />

  <div class="main-content">

    <Toast class="toast"/>
    <RouterView class="main-content"></RouterView>
  </div>
  <MainFooter />
</template>

<style scoped>
@import url('@/styles/global.css');


</style>
