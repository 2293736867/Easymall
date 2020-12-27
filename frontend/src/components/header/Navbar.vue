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
import router from "../../js/router/router";
export default {
    name: "Navbar",
    data(){
        return {
            activeIndex:"0",
            navbarContent:['首页','全部商品','手机数码','电脑平板','家用电器','汽车用品','食品材料','图书杂志','服装服饰','理财产品' ],
            links:['/','/all']
        }
    },
    methods:{
        handleSelect(key){
            router.push({
                path:this.links[key]
            })
        },
    }
}
</script>

<style scoped>

</style>