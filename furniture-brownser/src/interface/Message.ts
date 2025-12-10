export interface MessageInfo{
  name:string,
  telephone:string,
  email:string,
  text:string,
  itemList:number[]
}
export interface MessageResponse{
  id:number,
  username:string,
  name:string,
  telephone:string,
  email:string,
  text:string,
  createTime:string,
  itemList:string[]
}
export interface ItemResponse{
  id:number,
  name:string,
  status?:boolean
}