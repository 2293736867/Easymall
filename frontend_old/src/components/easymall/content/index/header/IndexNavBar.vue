<template>
    <el-menu ref="menu" :default-active="activeIndex" mode="horizontal" @select="handleSelect">
        <el-menu-item index="0">首页</el-menu-item>
        <el-menu-item v-for="i in categories" :index="i.index">{{ i.value }}</el-menu-item>
    </el-menu>
</template>

<script>
import {defineComponent} from 'vue'
import router from "../../../../../js/router/Router";
import Categories from "../../../../../js/constant/Categories";
import {useStore} from 'vuex'

export default defineComponent({
    name: "IndexNavBar",
    setup() {
        const activeIndex = "0"
        const categories = Categories
        const store = useStore()
        let links = []

        links.push('/')
        for (let i = 0; i < Categories.length; ++i) {
            links.push('/products/' + Categories[i].index)
        }
        const handleSelect = key => {
            if (key !== 0) {
                store.commit('categoryId', key)
            }
            router.push(links[key])
        }

        return {
            //data
            activeIndex, categories,

            //methods
            handleSelect
        }
    },
})
</script>

<style scoped>

</style>