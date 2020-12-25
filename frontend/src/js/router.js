import {createWebHistory,createRouter} from 'vue-router'
import Index from "../components/Index.vue";
import AllProductsContent from "../components/content/AllProductsContent.vue";
import IndexContent from "../components/content/IndexContent.vue";

const routerHistory = createWebHistory()

const router = createRouter({
    history:routerHistory,
    routes:[
        {
            path:'/',
            component:Index,
            children:[
                {
                    path:'/',
                    component:IndexContent
                },
                {
                    path:'/all',
                    component:AllProductsContent
                }
            ]
        },
    ]
})

export default router