import Vue from "vue"
import VueRouter from "vue-router"
import Login from "@/components/Login"
import Index from "@/components/Index"
import ProductHeader from "@/components/ProductHeader";
import ProductCard from "@/components/ProductCard";
import Product from "@/components/Product";

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
    },
    {
        path:'/product/header',
        component: ProductHeader
    },
    {
        path:'/product/body',
        component: ProductCard
    },
    {
        path:'/product',
        component: Product
    }
]

const router = new VueRouter({
    mode: 'history',
    routes: routes
})

export default router