/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
import http from './http'
import * as mock from './mock'
const demo = import.meta.env.VITE_DEMO_MODE === 'true'
export const getProducts = async () => demo ? mock.products : (await http.get('/pos/products')).data.data
export const getOrders = async () => demo ? mock.orders : (await http.get('/pos/orders')).data.data
export const getDashboard = async () => demo ? mock.dashboard : (await http.get('/pos/dashboard')).data.data
export const getStores = async () => demo ? mock.stores : (await http.get('/pos/stores')).data.data
export const getMembers = async () => demo ? mock.members : (await http.get('/pos/members')).data.data
