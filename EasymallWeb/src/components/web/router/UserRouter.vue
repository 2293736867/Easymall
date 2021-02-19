<template>
    <el-container>
        <el-header>
            <UserTopBar></UserTopBar>
        </el-header>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
import UserTopBar from "../content/user/header/UserTopBar.vue";
import {defineComponent} from 'vue'
export default defineComponent({
    name: "UserRouter",
    components: {UserTopBar}
})
</script>

<style scoped>

</style>