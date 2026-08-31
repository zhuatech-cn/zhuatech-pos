# 企业级 POS 支付结算与关班治理

本模块对支付渠道应结金额、渠道实结金额、现金应有与实盘、退款审批、拒付复核和交班签署进行统一校验。

`POST /api/enterprise/pos/settlement-close` 返回渠道差异、现金差异及 `CLOSE / REVIEW / HOLD` 决策。生产使用应接入支付机构账单、钱箱盘点、岗位分离和不可篡改审计记录。
