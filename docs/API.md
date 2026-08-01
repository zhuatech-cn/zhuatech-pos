# ZhuaTech POS API

Copyright © 2026 上海如静知华信息科技有限公司。

所有接口默认返回：

```json
{ "success": true, "message": "操作成功", "data": {} }
```

除登录接口外，请求头需要 `Authorization: Bearer <token>`。

| 方法 | 地址 | 说明 | 角色 |
| --- | --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT | 匿名 |
| GET | `/api/auth/me` | 当前用户 | 已登录 |
| GET | `/api/pos/products` | 可售商品与库存 | 已登录 |
| GET | `/api/pos/orders` | 最近 30 笔订单 | 已登录 |
| POST | `/api/pos/checkout` | 结账、扣减库存并生成订单 | ADMIN / MANAGER / CASHIER |
| GET | `/api/pos/dashboard` | 经营指标、趋势、品类与提醒 | ADMIN / MANAGER / AUDITOR |
| GET | `/api/pos/stores` | 门店经营列表 | 已登录 |
| GET | `/api/pos/members` | 会员列表 | 已登录 |
| GET | `/api/pos/shifts` | 班次列表 | 已登录 |

结账请求示例：

```json
{
  "storeName": "上海静安旗舰店",
  "registerNo": "POS-02",
  "memberMobile": "13800000000",
  "paymentMethod": "WECHAT",
  "discount": 4.80,
  "items": [
    { "sku": "FD-10021", "quantity": 2 },
    { "sku": "BK-20018", "quantity": 1 }
  ]
}
```

`paymentMethod` 可选 `WECHAT`、`ALIPAY`、`CASH`、`BANK_CARD`、`MIXED`。社区版接口返回模拟支付结果，不连接真实支付渠道。

## 班次对账

`POST /api/pos/shift-reconciliation`：核对钱箱应有额与实点额，返回差异、交班状态和经理复核要求。
