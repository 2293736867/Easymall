import {createWebHistory, createRouter} from 'vue-router'
import IndexRouter from "../../components/easymall/router/IndexRouter.vue";
import ProductIndex from "../../components/easymall/content/product/ProductIndex.vue";
import Index from "../../components/easymall/content/index/Index.vue";
import Product from "../../components/easymall/content/product/Product.vue";
import UserRouter from "../../components/easymall/router/UserRouter.vue";
import UserIndex from "../../components/easymall/content/user/UserIndex.vue";
import AdminRouter from "../../components/admin/router/AdminRouter.vue";
import AdminIndex from "../../components/admin/content/AdminIndex.vue";

const routerHistory = createWebHistory()

const router = createRouter({
    history: routerHistory,
    routes: [{
        path: '/',
        component: IndexRouter,
        children: [{
            path: '/',
            component: Index
        }, {
            path: '/products/:id',
            component: ProductIndex
        }, {
            path: '/product/:id',
            component: Product
        }
        ]
    }, {
        path: '/user',
        component: UserRouter,
        children: [{
            path: '/user',
            component: UserIndex
        }]
    }, {
        path: '/admin',
        component: AdminRouter,
        children: [{
            path: '/admin',
            component: AdminIndex
        }]
    }]
})

export default router