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
import {defineComponent} from 'vue'
import AdminHeader from "../content/header/AdminHeader.vue";

export default defineComponent({
    name: "AdminRouter",
    components: {AdminHeader}
})
</script>

<style scoped>

</style>