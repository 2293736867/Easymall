<template>
    <el-steps :active="active" align-center finish-status="success" style="margin-top: 2rem">
        <el-step description="这是管理面板的首页" title="首页"></el-step>
        <el-step description="您可以进行用户管理" title="用户管理"></el-step>
        <el-step description="您也可以进行商品管理" title="商品管理"></el-step>
        <el-step description="祝您使用愉快" title="开始吧"></el-step>
    </el-steps>
    <el-button :disabled="buttonDisabled" style="margin-top: 2rem" type="primary" @click="next">{{ buttonText }}
    </el-button>
</template>

<script>
import {defineComponent, ref} from 'vue'

export default defineComponent({
    name: "AdminHomeTabPane",
    setup() {
        const active = ref(0)
        const buttonDisabled = ref(false)
        const buttonText = ref('继续')

        const next = _ => {
            if (++active.value === 4) {
                buttonDisabled.value = true
            } else if (active.value === 3)
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