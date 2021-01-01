<template>
    <el-container>
        <el-header>
            <Header></Header>
        </el-header>
        <el-main style="margin-top: 4rem">
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
import Header from "../header/Header.vue";

export default {
    name: "IndexRouter",
    components: {Header},
}
</script>

<style scoped>
</style>