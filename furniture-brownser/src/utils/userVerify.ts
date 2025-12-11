// 全局输入验证
const LIMIT = {
  UsernameMaxLength:16,
  UsernameMinLength:4,

  PasswordMaxLength:16,
  PasswordMinLength:6,

  EmailMaxLength:30,
  EmailMinLength:5,

  NameMaxLength:50,
  NameMinLength:2,

  TelephoneMaxLength:11,
  TelephoneMinLength:11
} as const

const isUsernameValid = (username:string)=>
  username.length >= LIMIT.UsernameMinLength && username.length <= LIMIT.UsernameMaxLength;

const isPasswordValid = (password:string)=>
  password.length >= LIMIT.PasswordMinLength && password.length <= LIMIT.PasswordMaxLength;

const isEmailValid = (email:string) =>
  email.length >= LIMIT.EmailMinLength && email.length <= LIMIT.EmailMaxLength

const isNameValid = (name:string) =>
  name.length >= LIMIT.NameMinLength && name.length <= LIMIT.NameMaxLength

const isTelephoneValid = (telephone:string) =>
  telephone.length >= LIMIT.TelephoneMinLength && telephone.length <= LIMIT.TelephoneMaxLength;

export default{
  LIMIT,
  isUsernameValid,
  isPasswordValid,
  isEmailValid,
  isNameValid,
  isTelephoneValid
}