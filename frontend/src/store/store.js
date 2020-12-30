import { createStore } from 'vuex'

const store = createStore({
    state () {
        return {
            userToken:'',
            categoryId:'',
        }
    },
    mutations: {
        signIn(state,v){
            store.commit('set',{
                k:'userToken',
                v:v
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
    }
})

export default store