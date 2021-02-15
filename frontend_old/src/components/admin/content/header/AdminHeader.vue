<template>
    <el-row type="flex" justify="space-between">
        <el-col :span="2">
            <el-image src="/logo/Logo.jpg" @click="RouterUtils.toIndex()" style="margin-top: 0.3rem"></el-image>
        </el-col>
        <el-col :span="1">
            <el-tooltip content="返回首页" effect="light" placement="bottom">
                <el-button circle type="primary" icon="el-icon-right" @click="RouterUtils.toIndex()"></el-button>
            </el-tooltip>
        </el-col>
    </el-row>
</template>

<script>
import {defineComponent} from 'vue'
import RouterUtils from "../../../../js/utils/RouterUtils";

export default defineComponent({
    name: "AdminHeader",
    setup(){
        // const toIndex = _=> {
        //     RouterUtils.toIndex()
        // }

        return {
            // toIndex
        }
    },
})
</script>

<style scoped>

</style>