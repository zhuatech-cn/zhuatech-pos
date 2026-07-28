<!-- Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. -->
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'
import AppIcon from '../components/AppIcon.vue'
const mode=ref('cashier'), username=ref('cashier'), password=ref('Demo@2026'), router=useRouter(), auth=useAuthStore()
function change(next){ mode.value=next; username.value=next==='cashier'?'cashier':'manager'; password.value='Demo@2026' }
function login(){ auth.signIn(mode.value); router.push(mode.value==='cashier'?'/cashier/terminal':'/admin/dashboard') }
</script>
<template>
  <main class="login-screen">
    <section class="login-story">
      <div class="story-brand"><span class="zh-mark">ZH</span><div><strong>ZhuaTech POS</strong><small>知华科技零售运营平台</small></div></div>
      <div class="store-scene">
        <div class="scene-sign">静安旗舰店 <span>OPEN</span></div>
        <div class="scene-shelf"><i v-for="i in 18" :key="i"></i></div>
        <div class="scene-counter"><span></span><b>POS 02</b></div>
      </div>
      <div class="story-copy"><p>STORE OPERATIONS · 2026</p><h1>让每一笔交易，<br/>都回到经营现场。</h1><div><span>触屏收银</span><span>会员营销</span><span>多店经营</span></div></div>
      <footer>上海如静知华信息科技有限公司 · https://www.zhuatech.cn/</footer>
    </section>
    <section class="login-form-wrap">
      <form class="login-form" @submit.prevent="login">
        <p class="section-kicker">WELCOME BACK</p><h2>登录工作台</h2><p class="form-help">选择工作入口，演示环境已预置账号与业务数据。</p>
        <div class="entry-choice"><button type="button" :class="{active:mode==='cashier'}" @click="change('cashier')"><AppIcon name="cart"/><span><b>收银工作台</b><small>面向门店收银员</small></span></button><button type="button" :class="{active:mode==='admin'}" @click="change('admin')"><AppIcon name="chart"/><span><b>运营管理端</b><small>面向店长与总部</small></span></button></div>
        <label><span>账号</span><input v-model="username" autocomplete="username" /></label><label><span>密码</span><input v-model="password" type="password" autocomplete="current-password" /></label>
        <div class="login-options"><label><input type="checkbox" checked/> 记住本机</label><span>演示模式无需连接后端</span></div>
        <button class="primary-login" type="submit">进入{{mode==='cashier'?'收银台':'管理端'}} <span>→</span></button>
        <p class="license-note">仅限个人非商业学习、研究与技术交流。商业使用需获得上海如静知华信息科技有限公司书面授权。</p>
      </form>
    </section>
  </main>
</template>
