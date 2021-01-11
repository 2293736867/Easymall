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
import {defineComponent,ref} from 'vue'

export default defineComponent({
    name: "AdminHomeTabPane",
    setup(){
        const active = ref(0)
        const buttonDisabled = ref(false)
        const buttonText = ref('继续')

        function next(){
            if(++active.value === 4)
            {
                buttonDisabled.value = true
            }
            else if(active.value === 3)
                buttonText.value = '完成'
        }

        return {
            active, buttonDisabled, buttonText,
            next
        }
    }
})
</script>

<style scoped>

</style>