<template>
    <el-menu ref="menu" :default-active="activeIndex" mode="horizontal" @select="handleSelect">
        <el-menu-item index="0">首页</el-menu-item>
        <el-menu-item v-for="i in categories" :index="i.index">{{ i.value }}</el-menu-item>
    </el-menu>
</template>

<script>
import {onMounted,defineComponent} from 'vue'
import router from "../../../../../js/router/router";
import Categories from "../../../../../js/constant/Categories";

export default defineComponent({
    name: "IndexNavBar",
    setup() {
        const activeIndex = "0"
        const categories = Categories
        let links = []

        onMounted(_ => {
            links.push('/')
            for (let i = 0; i < Categories.length; ++i) {
                links.push('/products/' + Categories[i].index)
            }
        })

        return{
            activeIndex,
            links,
            categories
        }
    },
    methods: {
        handleSelect(key) {
            if (key !== 0) {
                this.$store.commit('categoryId', key)
            }
            router.push({
                path: this.links[key]
            })
        },
    }
})
</script>

<style scoped>

</style>