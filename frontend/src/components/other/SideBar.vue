<template>
    <el-menu :collapse="isCollapse" class="el-menu-vertical-demo side-bar" default-active="1-1-1" @close="handleClose"
             @open="handleOpen">
        <el-menu-item index="0">
            <i class="el-icon-full-screen" @mouseenter="isCollapse = !isCollapse"></i>
            <span>展开/收起</span>
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
    name: "SideBar"
}
</script>

<style scoped>

</style>