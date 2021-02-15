import router from "../router/Router";

const RouterUtils = {
    toIndex:_ =>{
        router.push('/')
    },
    toAdmin:_=>{
        router.push('/admin')
    },
    toUser:_=>{
        router.push('/user')
    },
    to:path=>{
        router.push(path)
    },
}

export default RouterUtils