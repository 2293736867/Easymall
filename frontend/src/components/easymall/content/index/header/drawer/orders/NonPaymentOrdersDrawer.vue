<template>
    <el-drawer>
        <el-divider></el-divider>
        <el-row justify="space-between" style="color: #606266" type="flex">
            <el-col :span="4">
                商品名字
            </el-col>
            <el-col :span="4">
                缩略图
            </el-col>
            <el-col :span="4">
                购买数量
            </el-col>
            <el-col :span="4">
                小结
            </el-col>
            <el-col :span="4">
                删除
            </el-col>
        </el-row>
        <el-divider></el-divider>
        <div>
            <el-row justify="space-between" type="flex">
                <el-col :span="4" style="margin-top: 0.8rem">
                    ccc
                </el-col>
                <el-col :span="4">
                    <el-badge :value="num">
                        <el-image :preview-src-list="getThumbnailList" :src="getSrc" fit="contain" style="width: 50px">
                            <template #placeholder>
                                <div>加载中...</div>
                            </template>
                            <template #error>
                                <el-image src="/default/Product.png"></el-image>
                                <div>加载失败</div>
                            </template>
                        </el-image>
                    </el-badge>
                </el-col>
                <el-col :span="4" style="margin-top: 0.8rem">
                    num
                </el-col>
                <el-col :span="4" style="margin-top: 0.8rem">
                    price
                </el-col>
                <el-col :span="4">
                    <el-popconfirm
                        cancelButtonText='否'
                        confirmButtonText='是'
                        icon="el-icon-info"
                        iconColor="red"
                        title="从订单中移除？"
                    >
                        <template #reference>
                            <el-button circle icon="el-icon-delete" type="danger"></el-button>
                        </template>
                    </el-popconfirm>
                </el-col>
            </el-row>
            <el-divider></el-divider>
        </div>
        <el-row justify="end" type="flex">
            <el-col :span="2" style="margin-right: 0.2rem">
                <el-popconfirm
                    cancel-button-text="否"
                    confirm-button-text="是"
                    icon="el-icon-error"
                    icon-color="#F56C6C"
                    title="取消的订单将归入未付款订单"
                >
                    <template #reference>
                        <el-button type="danger">取消</el-button>
                    </template>
                </el-popconfirm>
            </el-col>
            <el-col :span="2" style="margin-right: 3rem">
                <el-popconfirm
                    cancel-button-text="否"
                    confirm-button-text="是"
                    icon="el-icon-success"
                    icon-color="#409EFF"
                    title="确认付款？"
                >
                    <template #reference>
                        <el-button type="primary">付款</el-button>
                    </template>
                </el-popconfirm>
            </el-col>
        </el-row>
    </el-drawer>
</template>

<script>
export default {
    name: "NonPaymentOrdersDrawer",
    data() {
        return {
            getSrc: '/1.jpg',
            getThumbnailList: [
                '/default/Product.png', '/default/Product.png'
            ],
            num: 13
        }
    },
    methods:{

    }
}
</script>

<style scoped>

</style>