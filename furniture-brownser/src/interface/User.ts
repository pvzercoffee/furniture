
export interface LoginInfo{
  username:string,
  password:string
}
export interface LoginResponse{
  id:number,
  username?:string,
  email?:string,
  name?:string,
  telephone?:string,
  gender?:number,
  birthday?:string,
  token?:string
}

export interface SignupInfo{
  username:string,
  password:string,
  email:string,
  name:string,
  telephone:string,
  gender:number,
  birthday:string,
}

