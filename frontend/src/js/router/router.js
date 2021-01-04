import {createWebHistory,createRouter} from 'vue-router'
import IndexRouter from "../../components/router/IndexRouter.vue";
import ProductIndex from "../../components/content/product/ProductIndex.vue";
import Index from "../../components/content/index/Index.vue";
import Product from "../../components/content/product/Product.vue";
import UserRouter from "../../components/router/UserRouter.vue";
import UserIndex from "../../components/content/user/UserIndex.vue";

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
                    component:ProductIndex
                },
                {
                    path:'/product/:id',
                    component:Product
                }
            ]
        },
        {
            path:'/user',
            component:UserRouter,
            children:[
                {
                    path:'/user',
                    component: UserIndex
                }
            ]
        }
    ]
})

export default router