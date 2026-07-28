<!-- Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. -->
<script setup>
import { useRoute, useRouter } from 'vue-router'
import AppIcon from './AppIcon.vue'
import { useAuthStore } from '../stores/auth'
const route=useRoute(), router=useRouter(), auth=useAuthStore()
const groups=[
  {label:'经营',items:[{to:'/admin/dashboard',label:'经营总览',icon:'grid'},{to:'/admin/orders',label:'交易订单',icon:'receipt'},{to:'/admin/reports',label:'数据报表',icon:'chart'}]},
  {label:'商品与门店',items:[{to:'/admin/products',label:'商品中心',icon:'box'},{to:'/admin/stores',label:'门店管理',icon:'store'}]},
  {label:'顾客运营',items:[{to:'/admin/members',label:'会员中心',icon:'users'},{to:'/admin/promotions',label:'营销活动',icon:'tag'}]},
]
const pageTitle=()=>groups.flatMap(g=>g.items).find(i=>route.path===i.to)?.label || '零售管理'
function exit(){auth.signOut();router.push('/login')}
</script>
<template>
  <div class="admin-shell">
    <aside class="admin-side">
      <div class="admin-brand"><span class="zh-mark">ZH</span><div><strong>ZhuaTech</strong><small>POS RETAIL CLOUD</small></div></div>
      <div class="tenant-switch"><span>当前组织</span><b>知华零售演示组织</b><AppIcon name="arrow" :size="14"/></div>
      <nav v-for="group in groups" :key="group.label"><p>{{group.label}}</p><RouterLink v-for="item in group.items" :key="item.to" :to="item.to" :class="{active:route.path===item.to}"><AppIcon :name="item.icon" :size="18"/><span>{{item.label}}</span></RouterLink></nav>
      <div class="side-bottom"><a href="https://www.zhuatech.cn/" target="_blank">知华科技官网</a><span>社区源码版 v1.0.0</span></div>
    </aside>
    <section class="admin-stage">
      <header class="admin-top"><div><h1>{{pageTitle()}}</h1><p>数据更新于 15:06:28</p></div><div class="top-tools"><button class="icon-button"><AppIcon name="search"/></button><button class="icon-button notice"><AppIcon name="bell"/><i></i></button><div class="manager"><span>林</span><div><b>林店长</b><small>区域运营经理</small></div></div><button class="icon-button" @click="exit"><AppIcon name="logout"/></button></div></header>
      <main class="admin-content"><router-view /></main>
    </section>
  </div>
</template>
