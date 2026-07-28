<!-- Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. -->
<script setup>
import { computed, ref } from 'vue'
import { products } from '../../api/mock'
import AppIcon from '../../components/AppIcon.vue'
const categories=['常用','现制饮品','烘焙轻食','鲜食乳品','休闲零食','生活用品']
const active=ref('常用'), keyword=ref(''), member=ref({name:'沈女士',level:'黑金会员',points:2860}), showPay=ref(false), payMethod=ref('WECHAT'), paid=ref(false)
const cart=ref([
  {...products[0],qty:2},{...products[3],qty:1},{...products[6],qty:1}
])
const visible=computed(()=>products.filter(p=>(active.value==='常用'||p.category===active.value)&&(!keyword.value||p.name.includes(keyword.value)||p.barcode.includes(keyword.value))))
const subtotal=computed(()=>cart.value.reduce((sum,p)=>sum+p.price*p.qty,0)), memberDiscount=computed(()=>member.value?4.8:0), payable=computed(()=>subtotal.value-memberDiscount.value)
function add(product){const item=cart.value.find(i=>i.sku===product.sku); if(item)item.qty++; else cart.value.push({...product,qty:1})}
function step(item,delta){item.qty+=delta;if(item.qty<=0)cart.value=cart.value.filter(i=>i.sku!==item.sku)}
function finish(){paid.value=true;setTimeout(()=>{showPay.value=false;paid.value=false;cart.value=[]},1200)}
const payment=[{id:'WECHAT',label:'微信支付',icon:'wallet'},{id:'ALIPAY',label:'支付宝',icon:'scan'},{id:'BANK_CARD',label:'银行卡',icon:'card'},{id:'CASH',label:'现金',icon:'receipt'}]
</script>
<template>
  <div class="terminal-layout">
    <section class="catalog-pane">
      <div class="terminal-tools"><div class="scan-input"><AppIcon name="scan"/><input v-model="keyword" placeholder="扫描商品条码，或输入名称 / 助记码"/><kbd>F2</kbd></div><button class="outline-action"><AppIcon name="pause"/>挂单 <span>2</span></button><button class="outline-action"><AppIcon name="receipt"/>取单</button></div>
      <div class="catalog-body"><aside class="category-rail"><button v-for="category in categories" :key="category" :class="{active:active===category}" @click="active=category">{{category}}<small v-if="category==='常用'">12</small></button></aside>
        <div class="product-area"><div class="product-head"><div><h2>{{active}}</h2><p>{{visible.length}} 个可售商品 · 价格含税</p></div><button><AppIcon name="grid"/>商品列表</button></div>
          <div class="product-grid"><button v-for="product in visible" :key="product.sku" class="product-tile" @click="add(product)"><div class="product-visual" :class="product.colorCode"><span>{{product.short}}</span><small v-if="product.status==='LOW_STOCK'">仅余 {{product.stock}}</small></div><div class="product-copy"><b>{{product.name}}</b><span>{{product.sku}}</span><p><strong>¥{{product.price.toFixed(2)}}</strong><em>库存 {{product.stock}}</em></p></div></button></div>
        </div>
      </div>
    </section>
    <aside class="basket-pane">
      <div class="basket-head"><div><h2>当前订单</h2><span>共 {{cart.reduce((s,i)=>s+i.qty,0)}} 件</span></div><button @click="cart=[]">清空</button></div>
      <button class="member-card"><span class="member-avatar">沈</span><div><b>{{member.name}} <em>{{member.level}}</em></b><small>积分 {{member.points}} · 本单享 9 折会员价</small></div><AppIcon name="arrow" :size="15"/></button>
      <div class="cart-columns"><span>商品</span><span>数量</span><span>小计</span></div>
      <div class="cart-list" v-if="cart.length"><article v-for="item in cart" :key="item.sku"><div class="mini-product" :class="item.colorCode">{{item.short}}</div><div class="line-copy"><b>{{item.name}}</b><small>¥{{item.price.toFixed(2)}} / 件</small></div><div class="qty-step"><button @click="step(item,-1)"><AppIcon name="minus" :size="13"/></button><span>{{item.qty}}</span><button @click="step(item,1)"><AppIcon name="plus" :size="13"/></button></div><strong>¥{{(item.price*item.qty).toFixed(2)}}</strong></article></div>
      <div class="empty-cart" v-else><AppIcon name="cart" :size="38"/><b>购物车为空</b><span>扫描条码或从左侧选择商品</span></div>
      <div class="order-note"><button><AppIcon name="tag"/>整单优惠</button><button><AppIcon name="user"/>选择导购</button><button><AppIcon name="receipt"/>订单备注</button></div>
      <div class="basket-summary"><p><span>商品金额</span><b>¥{{subtotal.toFixed(2)}}</b></p><p><span>会员优惠</span><b class="discount">−¥{{memberDiscount.toFixed(2)}}</b></p><p class="payable"><span>应收金额</span><strong><small>¥</small>{{payable.toFixed(2)}}</strong></p><button class="checkout-button" :disabled="!cart.length" @click="showPay=true"><span>收款</span><b>¥{{payable.toFixed(2)}}</b><kbd>Space</kbd></button></div>
    </aside>
    <div class="modal-mask" v-if="showPay" @click.self="showPay=false"><section class="payment-dialog" :class="{success:paid}"><template v-if="!paid"><header><div><p>订单应收</p><h2>¥{{payable.toFixed(2)}}</h2></div><button @click="showPay=false"><AppIcon name="close"/></button></header><div class="payment-grid"><button v-for="method in payment" :key="method.id" :class="{active:payMethod===method.id}" @click="payMethod=method.id"><AppIcon :name="method.icon" :size="24"/><span>{{method.label}}</span><i v-if="payMethod===method.id"><AppIcon name="check" :size="12"/></i></button></div><div class="payment-code"><div class="fake-code"><i v-for="i in 49" :key="i" :class="{dark:[1,2,3,7,8,9,15,17,19,22,24,26,29,30,32,34,36,40,41,43,45,47,48].includes(i)}"></i></div><div><b>请顾客出示付款码</b><span>支持扫码枪录入，也可点击下方确认模拟支付</span><small>支付通道 · 演示环境</small></div></div><footer><button class="cancel-pay" @click="showPay=false">取消</button><button class="confirm-pay" @click="finish">确认收款 ¥{{payable.toFixed(2)}}</button></footer></template><template v-else><div class="success-mark"><AppIcon name="check" :size="34"/></div><h2>收款成功</h2><p>订单已完成，小票正在打印</p></template></section></div>
  </div>
</template>
