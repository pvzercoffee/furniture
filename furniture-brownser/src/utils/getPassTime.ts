export const getPassTime = (value:Date)=>{
  const time:number = value.getTime() / 1000;
  const now = new Date().getTime()/1000;

  if(now-time < 60) return `${Math.floor(now-time)}秒前`;
  else if(now-time < 60*60) return `${Math.floor((now-time)/60)}分钟前`;
  else if(now-time < 60*60*24) return `${Math.floor((now-time)/60/60)}小时前`;
  else if(now-time < 60*60*24*7) return `${Math.floor((now-time)/60/24/7)}天前`;
}