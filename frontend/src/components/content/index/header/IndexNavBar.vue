<template>
    <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
        <el-menu-item index="0">首页</el-menu-item>
        <el-menu-item index="1">全部商品</el-menu-item>
        <el-menu-item index="2">手机数码</el-menu-item>
        <el-menu-item index="3">电脑平板</el-menu-item>
        <el-menu-item index="4">家用电器</el-menu-item>
        <el-menu-item index="5">汽车用品</el-menu-item>
        <el-menu-item index="6">食品材料</el-menu-item>
        <el-menu-item index="7">图书杂志</el-menu-item>
        <el-menu-item index="8">服装服饰</el-menu-item>
        <el-menu-item index="9">理财产品</el-menu-item>
    </el-menu>
</template>

<script>
import router from "../../../../js/router/router";
import Categories from "../../../../js/constant/Categories";
export default {
    name: "IndexNavBar",
    data(){
        return {
            activeIndex:"0",
            navbarContent:[],
            links:[],
        }
    },
    mounted(){
        this.links.push('/')
        this.navbarContent.push('首页')
        for(var i=0;i<Categories.length;++i)
        {
            this.links.push('/products/'+Categories[i].index)
            this.navbarContent.push(Categories[i].value)
        }
    },
    methods:{
        handleSelect(key){
            if(key !== 0)
            {
                this.$store.commit('categoryId',key)
            }
            router.push({
                path:this.links[key]
            })
        },
    }
}
</script>

<style scoped>

</style>