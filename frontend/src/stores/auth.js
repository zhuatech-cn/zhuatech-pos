/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
import { defineStore } from 'pinia'
export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('zhuatech_pos_token') || '',
    user: JSON.parse(localStorage.getItem('zhuatech_pos_user') || 'null'),
  }),
  actions: {
    signIn(mode) {
      this.token = 'demo-token'
      this.user = mode === 'cashier'
        ? { fullName: '周雨晴', role: 'CASHIER', storeName: '上海静安旗舰店' }
        : { fullName: '林店长', role: 'MANAGER', storeName: '上海静安旗舰店' }
      localStorage.setItem('zhuatech_pos_token', this.token)
      localStorage.setItem('zhuatech_pos_user', JSON.stringify(this.user))
    },
    signOut() { this.token = ''; this.user = null; localStorage.removeItem('zhuatech_pos_token'); localStorage.removeItem('zhuatech_pos_user') },
  },
})
