import Vue from "vue"
import VueRouter from "vue-router"
import Index from "@/components/Index"
import ProductHeader from "@/components/product/ProductHeader";
import Product from "@/components/product/Product";
import ProductDetail from "@/components/product/ProductDetail";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: Index,
        props: true
    },
    {
        path:'/product/header',
        component: ProductHeader
    },
    {
        path:'/product',
        component: Product
    },
    {
        path:'/product/:id',
        component: ProductDetail
    }
]

const router = new VueRouter({
    mode: 'history',
    routes: routes
})

export default router