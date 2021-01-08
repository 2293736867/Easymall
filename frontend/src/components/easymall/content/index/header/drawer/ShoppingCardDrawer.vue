<template>
    <el-drawer direction="rtl" size="50%" title="购物车">
        <el-row>
            <el-col v-if="none">
                <el-empty description="购物车为空"></el-empty>
            </el-col>
            <el-col v-else>
                <el-divider></el-divider>
                <el-row justify="center" style="color: #606266" type="flex">
                    <el-col :span="4">是否购买</el-col>
                    <el-col :span="4">商品名字</el-col>
                    <el-col :span="4">缩略图</el-col>
                    <el-col :span="4">购买数量</el-col>
                    <el-col :span="4">小结</el-col>
                    <el-col :span="4">删除</el-col>
                </el-row>
                <el-divider></el-divider>
                <el-row justify="center" type="flex" v-loading="loadingProducts" v-for="p in products">
                    <el-col :span="4" style="margin-top: 0.9rem">
                        <el-switch v-model="checkbox"></el-switch>
                    </el-col>
                    <el-col :span="4" style="margin-top: 1rem">
                        {{ p.name }}
                    </el-col>
                    <el-col :span="4">
                        <el-badge :value="num">
                            <el-image :preview-src-list="getThumbnailList" :src="getSrc" fit="contain"
                                      style="width: 50px">
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
                        {{ parseFloat(p.price)*parseInt(p.num) }}
                    </el-col>
                    <el-col :span="4" style="margin-top: 0.3rem">
                        <el-popconfirm
                            cancelButtonText='否'
                            confirmButtonText='是'
                            icon="el-icon-info"
                            iconColor="red"
                            title="移出购物车？"
                        >
                            <template #reference>
                                <el-button circle icon="el-icon-delete" type="danger"></el-button>
                            </template>
                        </el-popconfirm>
                    </el-col>
                </el-row>
                <el-divider></el-divider>
                <el-row justify="end" type="flex">
                    <el-col :span="4">
                        <el-button type="primary" @click="checkout">结算</el-button>
                    </el-col>
                </el-row>
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
            getSrc: '/1.jpg',
            getThumbnailList: [
                '/default/Product.png', '/default/Product.png'
            ],
            productIds: [],
            products: [],
            loadingProducts: true,
            none: false,
        }
    },
    methods: {
        init() {
            this.productIds = this.$store.getters.shoppingCardProductIds
            if (this.productIds.length === 0) {
                this.none = true
            } else {
                axios.post(URL.productGetDetails, {
                    id: this.productIds
                }).then(res => {
                    this.products = res.data.data
                    this.loadingProducts = false
                })
            }
        },
        checkout() {
            this.$emit('checkout')
        },
    },
    emits: ['checkout']
}
</script>

<style scoped>

</style>