<template>
    <main>
        <BaseDialog class="dialog" ref="dialog" :message="dialogMessage"/>
        <p style="margin-left: 20px;">热销商品</p>
        <div class="show-box">
            <div class="show-left">
              <Double12Timeout />
            </div>
            <div class="show-right">
                <div class="goods" @click="showDetail(value)" v-for="value in productList">
                  <div class="good" :style="{backgroundImage:`url(${getImageUrl(value.img)})`}"></div>
                  <p class="good-text">￥{{ value.price }}</p>
                  <p class="good-text">{{ value.name }}</p>
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

const images = import.meta.glob('/src/photos/*',{
  eager:true,
  query:'?url',
  import:'default'
});

const productList = [
    {
        img: "pro1.png",
        name: "实木照片墙 创意墙面小墙贴纸",
        price: 400.00
    },
    {
        img: "pro2.png",
        name: "馨竹家居 茶几边的加厚丝毛绿地毯",
        price: 400.00
    },
    {
        img: "pro3.png",
        name: "可爱小脚丫 浴室门口防滑地垫脚垫",
        price: 100.00
    },
    {
        img: "pro4.png",
        name: "地中海客厅照片墙 地中海风情客厅",
        price: 300.00
    },
    {
        img: "pro5.png",
        name: "相框墙 装饰相框墙 新品组合套装",
        price: 990.00
    },
    {
        img: "pro6.png",
        name: "海藻绿 3d系列进门垫 卫生间地垫",
        price: 200.00
    },
    {
        img: "pro7.png",
        name: "可爱小脚丫 浴室门口防滑地垫脚垫",
        price: 100.00
    },
    {
        img: "pro8.png",
        name: "长绒素色地毯 卧室客厅床边地毯垫子",
        price: 100.00
    },
    {
        img: "pro9.jpg",
        name: "竹妃 纸买就送 116抽家庭装6包",
        price: 60.00
    }
];

const getImageUrl = (name:string):string => {
  return images[`/src/photos/${name}`] as string;
}

let dialogMessage:ProductInfo = reactive({
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

