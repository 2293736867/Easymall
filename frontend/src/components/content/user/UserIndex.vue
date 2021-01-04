<template>
    <el-tabs type="border-card">
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-help"></i>总览</span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-user"></i>个人信息</span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-shopping-cart-full">购物车</i></span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-star-off">收藏夹</i></span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-goods">订单详情</i></span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-delete">未付款</i></span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-remove">未发货</i></span>
            </template>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-checked">已发货</i></span>
            </template>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import OverviewTabPane from "./tabpane/OverviewTabPane.vue";
export default {
    name: "UserIndex",
    components: {OverviewTabPane},
}
</script>

<style scoped>

</style>