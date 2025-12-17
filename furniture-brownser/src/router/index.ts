import About from '@/pages/About.vue'
import Board from '@/pages/Board.vue'
import Home from '@/pages/Home.vue'
import Login from '@/pages/Login.vue'
import ShowProduct from '@/pages/ShowProduct.vue'
import Signup from '@/pages/Signup.vue'
import UserCenter from '@/pages/UserCenter.vue'

import {createRouter,createWebHistory} from 'vue-router'

const router = createRouter({
    history:createWebHistory('/furniture'),
    routes:[
        //关于我们
        {
            name:"about",
            path:"/about",
            component:About
        },
        //留言
        {
            name:"board",
            path:"/board",
            component:Board
        },
        //主页
        {
            name:"home",
            path:"/home",
            component:Home
        },
        //登录
        {
            name:"login",
            path:"/login",
            component:Login
        },
        //注册
        {
          name:"signup",
          path:"/signup",
          component:Signup
        },
        //个人中心
        {
          name:"user",
          path:"/user",
          component:UserCenter
        },
        //产品展示
        {
            name:"show",
            path:"/show",
            component:ShowProduct
        },

        {
            name:"/",
            redirect:"/home",
            path:"/"
        }
    ],
    scrollBehavior(to,from,savedPosition){
      //如果浏览器的前进/后退按钮有保存位置，优先滚动到保存位置
      if(savedPosition){
        return savedPosition;
      }
      else{
        //每次路由切换，滚动到顶部
        return {left:0,top:0}
      }
    }
});

export default router