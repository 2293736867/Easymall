<template>
    <el-drawer direction="rtl" size="50%" title="购物车">
        <el-divider></el-divider>
        <el-row type="flex" style="margin-left: 2rem">
            <el-col :span="3">
                <el-switch v-model="checkbox" style="margin-top: 4.8rem" active-text="确认购买"></el-switch>
            </el-col>
            <el-col :span="3">
                <el-form-item>cc</el-form-item>
                <div style="margin-top: 4.8rem">
                    商品名字
                </div>
            </el-col>
            <el-col :span="8">
                <el-badge :value="13">
                    <el-card v-loading="loading" element-loading-text="拼命加载中" shadow="hover">
                        <el-image src="/default/Product.png" fit="contain" style="width: 150px">
                            <template #placeholder>
                                <div>加载中...</div>
                            </template>
                            <template #error>
                                <el-image src="/default/Product.png"></el-image>
                                <div>加载失败</div>
                            </template>
                        </el-image>
                    </el-card>
                </el-badge>
            </el-col>
            <el-col :span="4">
                <el-input-number size="small" v-model="num" :min="1" style="margin-top: 4.5rem"></el-input-number>
            </el-col>
        </el-row>
        <el-divider></el-divider>
    </el-drawer>
</template>

<script>
export default {
    name: "ShoppingCardDrawer",
    data() {
        return {
            num: 1,
            checkbox:false,
        }
    },
    methods: {
        showDrawer() {
            console.log('init')
        }
    }
}
</script>

<style scoped>

</style>