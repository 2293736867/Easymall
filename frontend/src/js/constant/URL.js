const protocol = 'http'
const port = '8080'
const host = 'localhost'
const base = protocol+'://'+host+':'+port+'/'
const userBase = base + 'user/'
const productBase = base + 'product/'

const URL = {
    code:base+'verification/code',
    signIn:userBase+'sign/in',
    signUp:userBase+'sign/up',
    checkUsername:userBase+'check/username/',
    product:productBase+'get',
    productData:productBase+'data/',
    productDetail:productBase
}

export default URL