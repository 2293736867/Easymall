<template>
    <el-drawer size="20%" direction="ltr">
        <el-row justify="center" type="flex">
            <el-col :span="24">
                <el-image src="/default/User.png"></el-image>
            </el-col>
            <el-col :span="24">
                <el-button style="margin-top: 4rem" type="primary">修改</el-button>
            </el-col>
        </el-row>
    </el-drawer>
</template>

<script>
export default {
    name: "AvatarDrawer",
    data(){
        return{

        }
    },
    mounted() {

    }
}
</script>

<style scoped>

</style>