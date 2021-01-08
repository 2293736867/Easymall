import { createStore } from 'vuex'

const store = createStore({
    state () {
        return {
            userToken:'',
            categoryId:'',
            shoppingCardProductIds:[],
        }
    },
    mutations: {
        addShoppingCardNum(state,v){
            if(state.shoppingCardProductIds.length === 0)
            {
                state.shoppingCardProductIds.push(v)
            }
            else if(!state.shoppingCardProductIds.some(item=>{item === v.productId}))
            {
                state.shoppingCardProductIds.push(v.productId)
            }
        },
        signIn(state,v){
            store.commit('set',{
                k:'user',
                v:JSON.stringify(v.user)
            })
            store.commit('set',{
                k:'userToken',
                v:v.userToken
            })
        },
        categoryId(state, v){
            state.categoryId = v
        },
        signOut(){
            store.commit('remove','userToken')
        },
        set (state,value) {
            if(value.k === 'userToken')
            {
                state.userToken = value.v
            }
            localStorage.setItem(value.k,value.v)
        },
        remove(state,key){
            if(key === 'userToken')
            {
                state.userToken = ''
            }
            localStorage.removeItem(key)
        }
    },
    getters:{
        isUserSignIn(state){
            if(localStorage.getItem('userToken'))
                state.userToken = localStorage.getItem('userToken')
            return state.userToken !== ''
        },
        categoryId(state){
            return state.categoryId
        },
        shoppingCardNum(state){
            return state.shoppingCardProductIds.length
        },
        shoppingCardProductIds(state){
            return state.shoppingCardProductIds
        }
    }
})

export default store