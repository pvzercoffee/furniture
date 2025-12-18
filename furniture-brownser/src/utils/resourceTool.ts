const images = import.meta.glob('/src/*/*',{
  eager:true,
  query:'?url',
  import:'default'
});

const getImageUrl = (name:string):string => {
  return images[`/src/${name}`] as string;
}

export default{
  getImageUrl
}