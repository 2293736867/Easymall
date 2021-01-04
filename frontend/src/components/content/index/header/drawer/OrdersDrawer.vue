<template>
    <el-drawer direction="rtl" size="50%" title="订单">
        <el-row type="flex" justify="center" style="color: #606266">
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
            <el-row type="flex" justify="center">
                <el-col :span="4" style="margin-top: 0.8rem">
                    ccc
                </el-col>
                <el-col :span="4">
                    <el-badge :value="num">
                        <el-image fit="contain" :src="getSrc" :preview-src-list="getThumbnailList" style="width: 50px">
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
                        confirmButtonText='是'
                        cancelButtonText='否'
                        icon="el-icon-info"
                        iconColor="red"
                        title="从订单中移除？"
                    >
                        <template #reference>
                            <el-button icon="el-icon-delete" type="danger" circle></el-button>
                        </template>
                    </el-popconfirm>
                </el-col>
            </el-row>
            <el-divider></el-divider>
        </div>
        <el-row type="flex" justify="end">
            <el-col :span="2" style="margin-right: 0.2rem">
                <el-button type="danger">取消</el-button>
            </el-col>
            <el-col :span="2" style="margin-right: 3rem">
                <el-popconfirm
                    confirm-button-text="是"
                    cancel-button-text="否"
                    icon="el-icon-success"
                    icon-color="#409EFF"
                    title="确认付款？"
                    >
<!--                    icon-color=""-->
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
    name: "OrdersDrawer",
    data(){
        return{
            getSrc:'/1.jpg',
            getThumbnailList:[
                '/default/Product.png','/default/Product.png'
            ]
        }
    },
    methods:{
        init(){

        }
    }
}
</script>

<style scoped>

</style>