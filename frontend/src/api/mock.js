/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
export const products = [
  { sku:'FD-10021', barcode:'6901002100018', name:'海盐厚乳拿铁', category:'现制饮品', price:22, memberPrice:19.8, stock:48, status:'ACTIVE', colorCode:'sage', short:'厚乳' },
  { sku:'FD-10034', barcode:'6901003400018', name:'青提茉莉冰茶', category:'现制饮品', price:18, memberPrice:16.2, stock:36, status:'ACTIVE', colorCode:'mint', short:'茉莉' },
  { sku:'FD-10042', barcode:'6901004200018', name:'桂花燕麦澳白', category:'现制饮品', price:24, memberPrice:21.6, stock:31, status:'ACTIVE', colorCode:'cream', short:'澳白' },
  { sku:'BK-20018', barcode:'6902001800018', name:'黄油可颂', category:'烘焙轻食', price:12, memberPrice:10.8, stock:22, status:'ACTIVE', colorCode:'sand', short:'可颂' },
  { sku:'BK-20043', barcode:'6902004300018', name:'烟熏鸡肉恰巴塔', category:'烘焙轻食', price:26, memberPrice:23.4, stock:14, status:'ACTIVE', colorCode:'amber', short:'恰巴塔' },
  { sku:'BK-20055', barcode:'6902005500018', name:'黑松露火腿卷', category:'烘焙轻食', price:19.8, memberPrice:17.8, stock:17, status:'ACTIVE', colorCode:'cocoa', short:'火腿卷' },
  { sku:'FR-30012', barcode:'6903001200018', name:'草莓希腊酸奶杯', category:'鲜食乳品', price:16.8, memberPrice:15, stock:18, status:'ACTIVE', colorCode:'rose', short:'酸奶' },
  { sku:'FR-30027', barcode:'6903002700018', name:'冷萃咖啡 280ml', category:'鲜食乳品', price:15, memberPrice:13.5, stock:8, status:'LOW_STOCK', colorCode:'slate', short:'冷萃' },
  { sku:'FR-30039', barcode:'6903003900018', name:'低温鲜牛乳 250ml', category:'鲜食乳品', price:9.5, memberPrice:8.5, stock:26, status:'ACTIVE', colorCode:'blue', short:'鲜乳' },
  { sku:'SN-40008', barcode:'6904000800018', name:'海苔脆片', category:'休闲零食', price:9.9, memberPrice:8.9, stock:63, status:'ACTIVE', colorCode:'olive', short:'海苔' },
  { sku:'SN-40031', barcode:'6904003100018', name:'每日坚果 25g', category:'休闲零食', price:8, memberPrice:7.2, stock:52, status:'ACTIVE', colorCode:'cocoa', short:'坚果' },
  { sku:'LF-50006', barcode:'6905000600018', name:'便携湿巾 10 抽', category:'生活用品', price:5, memberPrice:4.5, stock:45, status:'ACTIVE', colorCode:'blue', short:'湿巾' },
]

export const orders = [
  { orderNo:'POS202607281458021', createdAt:'2026-07-28 14:58:02', storeName:'上海静安旗舰店', registerNo:'POS-02', cashierName:'周雨晴', memberName:'沈女士', paymentMethod:'WECHAT', status:'PAID', payable:48, subtotal:52.8, discount:4.8, itemCount:3 },
  { orderNo:'POS202607281454116', createdAt:'2026-07-28 14:54:11', storeName:'上海静安旗舰店', registerNo:'POS-02', cashierName:'周雨晴', memberName:null, paymentMethod:'ALIPAY', status:'PAID', payable:27.9, subtotal:27.9, discount:0, itemCount:2 },
  { orderNo:'POS202607281448392', createdAt:'2026-07-28 14:48:39', storeName:'上海静安旗舰店', registerNo:'POS-02', cashierName:'周雨晴', memberName:'陈先生', paymentMethod:'BANK_CARD', status:'PAID', payable:61.2, subtotal:68, discount:6.8, itemCount:4 },
  { orderNo:'POS202607281441508', createdAt:'2026-07-28 14:41:50', storeName:'上海静安旗舰店', registerNo:'POS-02', cashierName:'周雨晴', memberName:null, paymentMethod:'CASH', status:'PAID', payable:15, subtotal:15, discount:0, itemCount:1 },
  { orderNo:'POS202607281435146', createdAt:'2026-07-28 14:35:14', storeName:'上海静安旗舰店', registerNo:'POS-01', cashierName:'韩梅', memberName:'陆女士', paymentMethod:'MIXED', status:'PARTIAL_REFUND', payable:86.5, subtotal:92.5, discount:6, itemCount:6 },
  { orderNo:'POS202607281427339', createdAt:'2026-07-28 14:27:33', storeName:'上海徐汇滨江店', registerNo:'POS-03', cashierName:'徐安', memberName:null, paymentMethod:'WECHAT', status:'REFUNDED', payable:22, subtotal:22, discount:0, itemCount:1 },
]

export const stores = [
  { storeCode:'SH-JA-01', name:'上海静安旗舰店', city:'上海', address:'南京西路 1188 号', status:'OPEN', registerCount:4, onlineRegisters:3, todaySales:28640.5, todayOrders:382, targetRate:92 },
  { storeCode:'SH-XH-02', name:'上海徐汇滨江店', city:'上海', address:'龙腾大道 2266 号', status:'OPEN', registerCount:3, onlineRegisters:3, todaySales:22180, todayOrders:296, targetRate:86 },
  { storeCode:'HZ-HB-01', name:'杭州湖滨店', city:'杭州', address:'延安路 258 号', status:'OPEN', registerCount:3, onlineRegisters:3, todaySales:19872.6, todayOrders:268, targetRate:81 },
  { storeCode:'SZ-GY-03', name:'苏州工业园区店', city:'苏州', address:'苏州大道东 268 号', status:'OPEN', registerCount:2, onlineRegisters:2, todaySales:14890.2, todayOrders:201, targetRate:78 },
  { storeCode:'SH-PD-05', name:'上海前滩筹备店', city:'上海', address:'东育路 500 号', status:'PREPARING', registerCount:3, onlineRegisters:0, todaySales:0, todayOrders:0, targetRate:0 },
]

export const members = [
  { memberNo:'M202603180021', name:'沈女士', mobile:'138****2716', level:'黑金会员', points:2860, balance:328, totalSpend:12680.5, visits:46, lastVisit:'今天 14:58' },
  { memberNo:'M202605090116', name:'陈先生', mobile:'139****8832', level:'金卡会员', points:1680, balance:86.5, totalSpend:8240, visits:31, lastVisit:'今天 14:48' },
  { memberNo:'M202607110328', name:'陆女士', mobile:'136****5210', level:'银卡会员', points:620, balance:0, totalSpend:2380.6, visits:12, lastVisit:'今天 14:35' },
  { memberNo:'M202604020088', name:'张先生', mobile:'137****9361', level:'金卡会员', points:1390, balance:120, totalSpend:6920.8, visits:27, lastVisit:'昨天 19:22' },
]

export const dashboard = {
  summary:{ todaySales:85583.3, todayOrders:1147, averageTicket:74.61, activeStores:4, activeRegisters:11, refundOrders:2, newMembers:86 },
  hourly:[{hour:'09:00',sales:6840,orders:94},{hour:'11:00',sales:12860,orders:168},{hour:'13:00',sales:10320,orders:139},{hour:'15:00',sales:14980,orders:182},{hour:'17:00',sales:18420,orders:221},{hour:'19:00',sales:16280,orders:196}],
  categories:[{category:'即食饮品',amount:26480,ratio:31},{category:'烘焙轻食',amount:20560,ratio:24},{category:'鲜食乳品',amount:17120,ratio:20},{category:'休闲零食',amount:12840,ratio:15},{category:'生活用品',amount:8560,ratio:10}],
  alerts:[
    {level:'HIGH',title:'南京西路店 03 号机离线',detail:'连续 8 分钟未收到心跳，请检查网络与收银服务',owner:'运维值班'},
    {level:'MEDIUM',title:'冷萃咖啡库存低于安全线',detail:'全渠道可售 8 瓶，预计 16:40 售罄',owner:'商品运营'},
    {level:'LOW',title:'2 笔退款等待复核',detail:'单笔金额超过门店授权额度',owner:'区域经理'},
  ]
}

export const promotions = [
  { name:'会员咖啡第二杯半价', scope:'现制饮品 · 全部门店', type:'第 N 件折扣', period:'07/20—08/18', status:'RUNNING', orders:386, discount:4826 },
  { name:'工作日早餐组合', scope:'烘焙 + 指定饮品', type:'组合价', period:'长期 · 07:00—10:30', status:'RUNNING', orders:728, discount:6240 },
  { name:'新会员首单立减', scope:'新注册会员', type:'订单立减', period:'07/01—09/30', status:'RUNNING', orders:214, discount:2140 },
  { name:'夏日冰饮券包', scope:'指定冰饮', type:'优惠券', period:'08/01—08/31', status:'SCHEDULED', orders:0, discount:0 },
]
