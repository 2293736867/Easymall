import Vue from "vue"
import VueRouter from "vue-router"
import Login from "@/components/Login"
import Index from "@/components/Index"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: Index,
        props: true
    },
    {
        path: '/index/:val',
        name: 'index',
        component: Login,
        props: true
    }
]

const router = new VueRouter({
    mode: 'history',
    routes: routes
})

export default router