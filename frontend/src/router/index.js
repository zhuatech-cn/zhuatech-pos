/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import CashierShell from '../components/CashierShell.vue'
import AdminShell from '../components/AdminShell.vue'
const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: LoginView },
  { path: '/cashier', component: CashierShell, children: [
    { path: '', redirect: '/cashier/terminal' },
    { path: 'terminal', component: () => import('../views/cashier/CashierTerminal.vue') },
    { path: 'orders', component: () => import('../views/cashier/CashierOrders.vue') },
    { path: 'shift', component: () => import('../views/cashier/CashierShift.vue') },
  ] },
  { path: '/admin', component: AdminShell, children: [
    { path: '', redirect: '/admin/dashboard' },
    { path: 'dashboard', component: () => import('../views/admin/AdminDashboard.vue') },
    { path: 'products', component: () => import('../views/admin/AdminProducts.vue') },
    { path: 'orders', component: () => import('../views/admin/AdminOrders.vue') },
    { path: 'stores', component: () => import('../views/admin/AdminStores.vue') },
    { path: 'members', component: () => import('../views/admin/AdminMembers.vue') },
    { path: 'promotions', component: () => import('../views/admin/AdminPromotions.vue') },
    { path: 'reports', component: () => import('../views/admin/AdminReports.vue') },
  ] },
]
const router = createRouter({ history: createWebHistory(), routes, scrollBehavior: () => ({ top: 0 }) })
router.beforeEach((to) => {
  if (to.path === '/login' || import.meta.env.VITE_DEMO_MODE === 'true' || localStorage.getItem('zhuatech_pos_token')) return true
  return '/login'
})
export default router
