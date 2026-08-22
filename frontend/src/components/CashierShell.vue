<!-- Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ -->
<script setup>
import { useRoute, useRouter } from 'vue-router'
import AppIcon from './AppIcon.vue'
import { useAuthStore } from '../stores/auth'
const route = useRoute(), router = useRouter(), auth = useAuthStore()
const nav = [
  {to:'/cashier/terminal',label:'收银台',icon:'cart'},
  {to:'/cashier/orders',label:'订单',icon:'receipt'},
  {to:'/cashier/shift',label:'交班',icon:'clock'},
]
function exit(){ auth.signOut(); router.push('/login') }
</script>
<template>
  <div class="cashier-shell">
    <header class="cashier-header">
      <div class="cashier-brand"><span class="zh-mark">ZH</span><div><strong>ZhuaTech POS</strong><small>知华科技零售收银</small></div></div>
      <nav><RouterLink v-for="item in nav" :key="item.to" :to="item.to" :class="{active:route.path===item.to}"><AppIcon :name="item.icon" :size="17"/>{{item.label}}</RouterLink></nav>
      <div class="register-meta"><span class="online-dot"></span><div><b>上海静安旗舰店</b><small>POS-02 · 当班 周雨晴</small></div><button title="退出" @click="exit"><AppIcon name="logout" :size="17"/></button></div>
    </header>
    <main class="cashier-main"><router-view /></main>
    <footer class="cashier-footer"><span>设备在线 · 小票打印机就绪 · 钱箱已连接</span><span>上海如静知华信息科技有限公司 · 仅限个人非商业学习交流</span><time>2026-07-28 15:06</time></footer>
  </div>
</template>
