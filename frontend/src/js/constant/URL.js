const protocol = 'http'
const port = '8080'
const host = 'localhost'
const base = protocol+'://'+host+':'+port+'/'
const userBase = base + 'user/'
const productBase = base + 'product/'

const URL = {
    code:base+'verification/code',

    userSignIn:userBase+'sign/in',
    userSignUp:userBase+'sign/up',
    userData:userBase+'data/',
    userUpdate:userBase+'update',
    userGetEmail:userBase+'get/email',
    userGetNickname:userBase+'get/nickname',
    userCheckUsername:userBase+'check/username/',

    productGetDetail:productBase+'get/detail/',
    productGetByCategory:productBase+'get/category/',
    productData:productBase+'data/',
}

export default URL