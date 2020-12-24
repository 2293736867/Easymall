import Index from '../components/Index.vue'
Vue.use(VueRouter)

const routes = [
    {
        path:'/',
        component:Index
    }
]

const router = new VueRouter({
    mode:'history',
    routes:routes
})

export default router