<template>
    <el-container>
        <el-header>
            <AdminHeader></AdminHeader>
        </el-header>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
import AdminHeader from "../content/header/AdminHeader.vue";
export default {
    name: "AdminRouter",
    components: {AdminHeader}
}
</script>

<style scoped>

</style>