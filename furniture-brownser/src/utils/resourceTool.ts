const images = import.meta.glob('/src/photos/*',{
  eager:true,
  query:'?url',
  import:'default'
});

const getImageUrl = (name:string):string => {
  return images[`/src/photos/${name}`] as string;
}

export default{
  getImageUrl
}