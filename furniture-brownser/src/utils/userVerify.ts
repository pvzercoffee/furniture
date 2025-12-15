// 全局输入验证
const LIMIT = {
  usernameLimit:{
    pattern:/^[A-Za-z0-9]{4,16}$/,
    msg:'只能输入字母或数字，4-16个'
  },
  passwordLimit:{
    pattern:/^[A-Za-z0-9]{6,12}$/,
    msg:'只能输入字母或数字，6-12个'
  },
  emailLimit:{
    pattern:/^\w+([.-]?\w+)*@\w+([.-]?\w+)*\.\w{2,}$/,
    msg:'请输入规范邮箱地址'
  },
  nameLimit:{
    pattern:/^\S{2,50}$/,
    msg:'不能包含空格，2-50个字'
  },
  telephoneLimit:{
    pattern:/^1[3-9]\d{9}$/,
    msg:'输入规范的中国大陆11位手机号'
  }
} as const;


const isUsernameValid = (username:string)=> LIMIT.usernameLimit.pattern.test(username);

const isPasswordValid = (password:string)=> LIMIT.passwordLimit.pattern.test(password);

const isEmailValid = (email:string) => LIMIT.emailLimit.pattern.test(email);

const isNameValid = (name:string) => LIMIT.nameLimit.pattern.test(name);

const isTelephoneValid = (telephone:string) => LIMIT.telephoneLimit.pattern.test(telephone);

export default{
  LIMIT,
  isUsernameValid,
  isPasswordValid,
  isEmailValid,
  isNameValid,
  isTelephoneValid
}