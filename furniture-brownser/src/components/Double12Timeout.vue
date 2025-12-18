<template>
<div class="double12">
    <h3 id="double12_text">{{ timeout }}</h3>
</div>
</template>

<script setup lang="ts">
import { useCountdown } from '@/composables/useCountdown';

let timeout = useCountdown('2026-12-12 00:00:00');

</script>

<style scoped>
.double12{
    width: 100%;
    height: 600px;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    background-image: url('@/images/daojishi.jpg');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    color: #fff;
    /* margin:20px; */
}
#double12_text{
    font-size: 30px;
    white-space: pre-wrap;
    text-align: center;

}
</style>