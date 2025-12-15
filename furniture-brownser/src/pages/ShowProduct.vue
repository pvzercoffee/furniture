<template>
    <main>
        <BaseDialog class="dialog" ref="dialog" :message="dialogMessage"/>
        <p style="margin-left: 20px;">热销商品</p>
        <div class="show-box">
            <div class="show-left">
              <Double12Timeout />
            </div>
            <div class="show-right">
              <div class="goods"  v-for="value in productList" :key="value.id"  @click="showDetail(value)">
                <ProductCard  :value="value"/>
              </div>
            </div>
            <div class="show-empty"></div>
        </div>

    </main>
</template>
<script setup lang="ts">
import Double12Timeout from '@/components/Double12Timeout.vue';
import BaseDialog from '@/components/BaseDialog.vue';
import { reactive, ref } from 'vue';
import type { ProductInfo } from '@/interface/DialogInfo';
import '@/styles/showProducts.css'
import ProductCard from '@/components/ProductCard.vue';
import resourceTool from '@/utils/resourceTool';
import { productList } from '@/constants/productData';

const {getImageUrl} = resourceTool;

let dialogMessage:ProductInfo = reactive({
  id:'',
  name:'',
  img:'',
  price:0
})

let dialog = ref();

function showDetail(value:ProductInfo){

  dialogMessage.name = value.name
  //类型守卫：a = b === 'string' ? b : ''
  //类型断言 a = b as string
  dialogMessage.img = getImageUrl(value.img);
  dialogMessage.price = value.price

  dialog.value.show();

}

</script>