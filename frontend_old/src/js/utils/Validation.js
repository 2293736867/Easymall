import REG from "../constant/REG";
import Utils from "./Utils";
import axios from 'axios'
import URL from "../constant/URL";

const Validation = {
    isNum: value => {
        return REG.positiveFloat.test(value) || REG.negativeFloat.test(value)
    },
    isNegative:value=>{
        return REG.negativeFloat.test(value)
    },
    isLessEqualThan:(value,max)=>{
        return parseFloat(value) <= parseFloat(max)
    },
    emailCheck:value=>{
        let result = false
        let message = ''
        if (!value) {
            message = '请输入邮箱'
        } else if (!REG.email.test(value)) {
            message = '请输入合法的邮箱'
        } else {
            result = true
        }
        return Utils.validatedResult(result,message)
    },
    usernameCheck:value=>{
        return new Promise(((resolve, reject) => {
            if (!value) {
                reject('请输入用户名')
            } else if (!REG.username.test(value)) {
                reject('用户名非法')
            } else {
                axios.get(URL.userCheckUsername + value).then(res => {
                    if (Utils.responseCodeEquals(res, 100104)) {
                        reject('用户名已存在')
                    } else {
                        resolve()
                    }
                })
            }
        }))
    }
}

export default Validation