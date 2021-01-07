<template>
    <el-drawer direction="rtl" size="50%" title="购物车">
        <el-divider></el-divider>
            <el-row type="flex" justify="center" style="color: #606266">
                <el-col :span="4">
                    是否购买
                </el-col>
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
                <el-col :span="4" style="margin-top: 0.9rem">
                    <el-switch v-model="checkbox"></el-switch>
                </el-col>
                <el-col :span="4" style="margin-top: 1rem">
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
                <el-col :span="4" style="margin-top: 0.5rem">
                    <el-input-number v-model="num" :min="1" size="small"></el-input-number>
                </el-col>
                <el-col :span="4" style="margin-top: 0.9rem">
                    price
                </el-col>
                <el-col :span="4" style="margin-top: 0.3rem">
                    <el-popconfirm
                        confirmButtonText='是'
                        cancelButtonText='否'
                        icon="el-icon-info"
                        iconColor="red"
                        title="移出购物车？"
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
            <el-col :span="4">
                <el-button type="primary" @click="checkout">结算</el-button>
            </el-col>
        </el-row>
    </el-drawer>

</template>

<script>
import axios from "axios";
import URL from "../../../../../../js/constant/URL";

export default {
    name: "ShoppingCardDrawer",
    data() {
        return {
            num: 1,
            checkbox: false,
            getSrc:'/1.jpg',
            getThumbnailList:[
                '/default/Product.png','/default/Product.png'
            ],
            productIds:[],
            products:[],
        }
    },
    methods: {
        init() {
            this.productIds = this.$store.getters.shoppingCardProductIds
            this.productIds.push('23423432')
            axios.post(URL.productGetDetails,{
                id:this.productIds
            }).then(res=>{
                console.log(res)
                this.products = res.data.data
            })
        },
        checkout(){
            this.$emit('checkout')
        },
    },
    emits:['checkout']
}
</script>

<style scoped>

</style>