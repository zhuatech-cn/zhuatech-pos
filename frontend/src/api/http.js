/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
import axios from 'axios'
const http = axios.create({ baseURL: '/api', timeout: 12000 })
http.interceptors.request.use((config) => {
  const token = localStorage.getItem('zhuatech_pos_token')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})
export default http
