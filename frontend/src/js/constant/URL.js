const protocol = 'http'
const port = '8080'
const host = 'localhost'
const base = protocol+'://'+host+':'+port+'/'
const userBase = base + 'user/'
const productBase = base + 'product/'
const shoppingCardBase = base + 'shopping/card/'

const URL = {
    code:base+'verification/code',

    userSignIn:userBase+'sign/in',
    userSignUp:userBase+'sign/up',
    userUpdate:userBase+'update',
    userDelete:userBase+'delete/',
    userGetAll:userBase+'get',
    userAdd:userBase+'add',
    userCheckUsername:userBase+'check/username/',

    productGetDetail:productBase+'get/detail/',
    productGetByCategory:productBase+'get/category/',
    productGetDetails:productBase+'get/details/',
    productUpdate:productBase+'update/',
    productGetAll:productBase+'get',
    productAdd:productBase+'add',
    productDelete:productBase+'delete/',

    shoppingCardAdd:shoppingCardBase+'add',
    shoppingCardGet:shoppingCardBase+'get'
}

export default URL