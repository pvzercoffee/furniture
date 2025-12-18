import { onMounted, onUnmounted, ref } from "vue";

const splitTime = 1000; //更新间隔时间

export const useCountdown = (targetDate:string)=>{

  const time = new Date(targetDate).getTime();

  let timeout = ref('正在计算。。');  //初始化倒计时文字

  let interval:number | null = null;  //初始化interval

  //更新最新日期与目标日期在倒计时文字上的结果的函数
  const update = ()=>{
    const now = new Date().getTime();

    const calculation = time-now;

    if(calculation < 0){
      timeout.value = '已结束';
      return;
    }

    const s = calculation/1000;
    const days = Math.floor(s/(60*60*24))
    const hours = Math.floor((s % (60 * 60 * 24))/ (60 * 60))
    const minutes = Math.floor((s % (60 * 60)) / 60)
    const seconds = Math.floor(s % 60)

    timeout.value = `${days}天${hours}时${minutes}分${seconds}秒`;
  }

  //当调用组件挂载时启动定时器
  onMounted(()=>{
    update();
    interval = setInterval(update,splitTime);
  });

  //当卸载定时器
  onUnmounted(()=>{
    if(interval) clearInterval(interval)
  });

  return timeout;

}