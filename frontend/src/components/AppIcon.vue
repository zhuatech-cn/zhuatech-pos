<!-- Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. -->
<script setup>
defineProps({ name: { type: String, required: true }, size: { type: Number, default: 18 } })
const paths = {
  grid:'M4 4h6v6H4zm10 0h6v6h-6zM4 14h6v6H4zm10 0h6v6h-6z', cart:'M3 4h2l2 11h10l3-8H6m3 12a1 1 0 1 0 0 .01M17 19a1 1 0 1 0 0 .01',
  receipt:'M6 3h12v18l-3-2-3 2-3-2-3 2V3zm3 5h6m-6 4h6', clock:'M12 3a9 9 0 1 0 9 9 9 9 0 0 0-9-9zm0 4v5l4 2',
  search:'M11 4a7 7 0 1 0 0 14 7 7 0 0 0 0-14zm5 12 5 5', user:'M12 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm-7 9a7 7 0 0 1 14 0',
  scan:'M4 8V4h4m8 0h4v4M4 16v4h4m8 0h4v-4M8 12h8', minus:'M5 12h14', plus:'M12 5v14M5 12h14',
  store:'M4 10v10h16V10M3 4h18l-2 6H5L3 4zm5 9v7m8-7v7', box:'m4 7 8-4 8 4-8 4-8-4zm0 0v10l8 4 8-4V7m-8 4v10',
  users:'M9 11a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm-6 9a6 6 0 0 1 12 0m3-8a3 3 0 1 0 0-6m-1 3a5 5 0 0 1 8 4',
  tag:'M4 4h7l9 9-7 7-9-9V4zm4 4a1 1 0 1 0 0 .01', chart:'M4 20V9m6 11V4m6 16v-7m5 7H2', settings:'M12 8a4 4 0 1 0 0 8 4 4 0 0 0 0-8zm0-5v2m0 14v2M3 12h2m14 0h2M5.6 5.6 7 7m10 10 1.4 1.4m0-12.8L17 7M7 17l-1.4 1.4',
  bell:'M6 17h12l-2-3V9a4 4 0 0 0-8 0v5l-2 3zm4 3h4', logout:'M10 5H5v14h5m4-3 4-4-4-4m4 4H9',
  card:'M3 6h18v12H3V6zm0 4h18', wallet:'M3 6h16v14H3V6zm0 3h18m-5 4h2', check:'m5 12 4 4L19 6', close:'m6 6 12 12M18 6 6 18', arrow:'m9 6 6 6-6 6', printer:'M7 8V3h10v5m0 8v5H7v-5m-3 0V9h16v7h-3M8 12h8', pause:'M8 5v14m8-14v14', refund:'M7 7H3v-4m0 4a9 9 0 1 1-1 8', filter:'M4 5h16l-6 7v6l-4 2v-8L4 5z'
}
</script>
<template><svg :width="size" :height="size" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"><path :d="paths[name]" /></svg></template>
