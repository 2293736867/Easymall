<template>
    <el-tabs type="border-card" ref="tabs">
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-help"></i>总览</span>
            </template>
            <OverviewTabPane></OverviewTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-user"></i>个人信息</span>
            </template>
            <PersonalTabPane></PersonalTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-shopping-cart-full">购物车</i></span>
            </template>
            <ShoppingCardTabPane></ShoppingCardTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-star-off">收藏夹</i></span>
            </template>
            <FavouriteTabPane></FavouriteTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-goods">订单详情</i></span>
            </template>
            <OrdersIndexTabPane @toOrders="toOrders"></OrdersIndexTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-delete">未付款</i></span>
            </template>
            <NonPaymentOrdersTabPane></NonPaymentOrdersTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-remove">已付款</i></span>
            </template>
            <PaidOrdersTabPane></PaidOrdersTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder">已发货</i></span>
            </template>
            <DeliveredOrdersTabPane></DeliveredOrdersTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-checked">已完成</i></span>
            </template>
            <FinishedOrdersTabPane></FinishedOrdersTabPane>
        </el-tab-pane>
        <el-tab-pane>
            <template #label>
                <span><i class="el-icon-folder-opened">未评价</i></span>
            </template>
            <NonReviewsOrdersTabPane></NonReviewsOrdersTabPane>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import OverviewTabPane from "./tabpane/OverviewTabPane.vue";
import PersonalTabPane from "./tabpane/PersonalTabPane.vue";
import OrdersIndexTabPane from "./tabpane/OrdersIndexTabPane.vue";
import NonPaymentOrdersTabPane from "./tabpane/NonPaymentOrdersTabPane.vue";
import PaidOrdersTabPane from "./tabpane/PaidOrdersTabPane.vue";
import DeliveredOrdersTabPane from "./tabpane/DeliveredOrdersTabPane.vue";
import FinishedOrdersTabPane from "./tabpane/FinishedOrdersTabPane.vue";
import NonReviewsOrdersTabPane from "./tabpane/NonReviewsOrdersTabPane.vue";
import ShoppingCardTabPane from "./tabpane/ShoppingCardTabPane.vue";
import FavouriteTabPane from "./tabpane/FavouriteTabPane.vue";

export default {
    name: "UserIndex",
    components: {
        FavouriteTabPane,
        ShoppingCardTabPane,
        NonReviewsOrdersTabPane,
        FinishedOrdersTabPane,
        DeliveredOrdersTabPane,
        PaidOrdersTabPane, NonPaymentOrdersTabPane, OrdersIndexTabPane, PersonalTabPane, OverviewTabPane},
    mounted() {
    },
    methods:{
        toOrders(index){
            this.$refs.tabs.currentName = 5 + index + ""
        }
    }
}
</script>

<style scoped>

</style>