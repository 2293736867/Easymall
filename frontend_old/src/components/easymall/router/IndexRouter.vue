<template>
    <el-container>
        <el-header>
            <IndexTopBar></IndexTopBar>
            <IndexNavBar style="margin-top: 1rem"></IndexNavBar>
        </el-header>
        <el-main style="margin-top: 4rem">
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
import IndexTopBar from "../content/index/header/IndexTopBar.vue";
import IndexNavBar from "../content/index/header/IndexNavBar.vue";
import {defineComponent} from 'vue'

export default defineComponent({
    name: "IndexRouter",
    components: {IndexNavBar, IndexTopBar}
})
</script>

<style scoped>
</style>