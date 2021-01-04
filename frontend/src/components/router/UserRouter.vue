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
export default {
    name: "UserRouter",
    components: {UserTopBar}
}
</script>

<style scoped>

</style>