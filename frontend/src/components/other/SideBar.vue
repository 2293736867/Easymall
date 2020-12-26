<template>
    <el-menu :collapse="isCollapse" class="el-menu-vertical-demo side-bar" default-active="1-1-1">
        <el-menu-item index="0">
            <el-badge :value="cardNum">
                <i class="el-icon-shopping-cart-1"></i>
            </el-badge>
        </el-menu-item>
        <el-menu-item>
            <el-badge :value="favoriteNum">
                <i class="el-icon-star-off"></i>
            </el-badge>
        </el-menu-item>
        <el-submenu index="1">
            <template #title>
                <i class="el-icon-location"></i>
                <span>cc</span>
            </template>
            <el-menu-item-group>
                <template #title>分组</template>
                <el-menu-item index="1-1">选项1-1</el-menu-item>
                <el-menu-item index="1-2">选项1-2</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>

<script>
export default {
    name: "SideBar",
    data() {
        return {
            isCollapse: true,
            cardNum:'12',
            favoriteNum:'12',
        }
    },
    methods: {}
}
</script>

<style scoped>

</style>