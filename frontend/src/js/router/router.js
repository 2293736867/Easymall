import {createWebHistory,createRouter} from 'vue-router'
import IndexRouter from "../../components/indexrouter/IndexRouter.vue";
import AllProducts from "../../components/content/product/Products.vue";
import Index from "../../components/content/index/Index.vue";
import Product from "../../components/content/product/Product.vue";
import Personal from "../../components/content/user/Personal.vue";

const routerHistory = createWebHistory()

const router = createRouter({
    history:routerHistory,
    routes:[
        {
            path:'/',
            component:IndexRouter,
            children:[
                {
                    path:'/',
                    component:Index
                },
                {
                    path:'/products/:id',
                    component:AllProducts
                },
                {
                    path:'/product/:id',
                    component:Product
                }
            ]
        },
        {
            path:'/personal',
            component:Personal
        }
    ]
})

export default router