<template>
    <el-steps :active="active" finish-status="success" align-center style="margin-top: 2rem">
        <el-step title="首页" description="这是管理面板的首页"></el-step>
        <el-step title="用户管理" description="您可以进行用户管理"></el-step>
        <el-step title="商品管理" description="您也可以进行商品管理"></el-step>
        <el-step title="开始吧" description="祝您使用愉快"></el-step>
    </el-steps>
    <el-button type="primary" @click="next" style="margin-top: 2rem" :disabled="buttonDisabled">{{buttonText}}</el-button>
</template>

<script>
export default {
    name: "AdminHomeTabPane",
    data(){
        return{
            active:0,
            buttonDisabled:false,
            buttonText:'继续'
        }
    },
    methods:{
        next(){
            if(++this.active === 4)
            {
                this.buttonDisabled = true
            }
            else if(this.active === 3)
                this.buttonText = '完成'
        }
    }
}
</script>

<style scoped>

</style>