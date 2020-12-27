import {createWebHistory,createRouter} from 'vue-router'
import IndexRouter from "../../components/indexrouter/IndexRouter.vue";
import AllProducts from "../../components/content/products/AllProducts.vue";
import Index from "../../components/content/index/Index.vue";
import Product from "../../components/content/products/Product.vue";

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
                    path:'/all',
                    component:AllProducts
                },
                {
                    path:'/product',
                    component:Product
                }
            ]
        }
    ]
})

export default router