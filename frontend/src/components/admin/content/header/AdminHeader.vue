<template>
    <el-row type="flex" justify="space-between">
        <el-col :span="2">
            <el-image src="/logo/Logo.jpg" @click="toIndex" style="margin-top: 0.3rem"></el-image>
        </el-col>
        <el-col :span="1">
            <el-tooltip content="返回首页" effect="light" placement="bottom">
                <el-button circle type="primary" icon="el-icon-right" @click="toIndex"></el-button>
            </el-tooltip>
        </el-col>
    </el-row>
</template>

<script>
import router from "../../../../js/router/router";
import {defineComponent} from 'vue'

export default defineComponent({
    name: "AdminHeader",
    setup(){
        function toIndex(){
            router.push({
                path:'/'
            })
        }

        return {
            toIndex
        }
    },
})
</script>

<style scoped>

</style>