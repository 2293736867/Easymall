<template>
    <el-drawer destroy-on-close direction="rtl" size="50%" title="购物车">
        <el-row>
            <el-col v-if="noneProduct">
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
                <el-row v-loading="loadingProducts">
                    <el-col v-for="(p,i) in products">
                        <el-row justify="center" type="flex">
                            <el-col :span="4" style="margin-top: 0.9rem">
                                <el-switch v-model="checkbox[i]"></el-switch>
                            </el-col>
                            <el-col :span="4">
                                {{ p.name }}
                            </el-col>
                            <el-col :span="4">
                                <el-badge :value="num[i]">
                                    <el-image :preview-src-list="getCoverImageList(p)" :src="getThumbnail(p)"
                                              fit="contain"
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
                                <el-input-number v-model="num[i]" :min="1" size="small"></el-input-number>
                            </el-col>
                            <el-col :span="4" style="margin-top: 0.9rem">
                                {{ p.price * num[i] }}
                            </el-col>
                            <el-col :span="4" style="margin-top: 0.3rem">
                                <el-popconfirm
                                    cancelButtonText='否'
                                    confirmButtonText='是'
                                    icon="el-icon-info"
                                    iconColor="red"
                                    title="移出购物车？"
                                    @confirm="removeFromShoppingCard(i)"
                                >
                                    <template #reference>
                                        <el-button circle icon="el-icon-delete" type="danger"></el-button>
                                    </template>
                                </el-popconfirm>
                            </el-col>
                        </el-row>
                        <el-divider></el-divider>
                    </el-col>
                </el-row>
                <el-row justify="space-between" type="flex">
                    <el-col :span="16">
                        <el-progress v-if="checkoutPercentage !== 0" :percentage="checkoutPercentage"
                                     :stroke-width="14" :text-inside="true"
                                     style="margin-top: 0.7rem;margin-left: 2.2rem"></el-progress>
                    </el-col>
                    <el-col :span="4">
                        <el-button v-loading="loadingCheckout" element-loading-background="rgba(0, 0, 0, 0.6)"
                                   type="primary" @click="checkout">结算
                        </el-button>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </el-drawer>

</template>

<script>
import axios from "axios";
import URL from "../../../../../../js/constant/URL";
import {defineComponent, ref} from 'vue'
import Utils from "../../../../../../js/utils/Utils";
import {ElNotification} from 'element-plus'
import {useStore} from 'vuex'

export default defineComponent({
    name: "ShoppingCardDrawer",
    setup(props, context) {
        const num = ref([])
        const checkbox = ref([])
        const products = ref([])
        const loadingProducts = ref(true)
        const noneProduct = ref(false)
        const loadingCheckout = ref(false)
        const checkoutPercentage = ref(0)

        const store = useStore()

        const init = _ => {
            axios.get(URL.shoppingCardGet, {
                headers: {
                    userToken: localStorage.getItem('userToken')
                }
            }).then(res => {
                if (Utils.responseCodeEquals(res, 120100)) {
                    const responseLength = Utils.getResponseDataLength(res)
                    if (responseLength === 0) {
                        noneProduct.value = true
                    } else {
                        products.value = Utils.getResponseData(res)
                        for (let i = 0; i < responseLength; ++i) {
                            checkbox.value.push(false)
                            num.value.push(1)
                        }
                        loadingProducts.value = false
                        store.commit('set', {
                            k: 'shoppingCardProducts',
                            v: JSON.stringify(res.data.data)
                        })
                    }
                } else if (Utils.responseCodeEquals(res, 120101)) {
                    noneProduct.value = true
                }
            })
        }

        const getThumbnail = p => {
            return p.thumbnailImageBaseUrl + "1"
        }

        const getCoverImageList = p => {
            let list = []
            for (let i = 0; i < p.coverImagesSize; ++i)
                list.push(p.coverImageBaseUrl + (i + 1))
            return list
        }

        const checkout = _ => {
            loadingCheckout.value = true
            let interval = setInterval(_ => {
                ++checkoutPercentage.value
                if (checkoutPercentage.value === 100) {
                    clearInterval(interval)
                    checkoutPercentage.value = 0
                }
            }, 30)
            setTimeout(_ => {
                ElNotification.success({
                    title: '购买成功',
                    message: '请查看您的订单',
                    position: 'top-left'
                })
                for (let i = 0; i < this.checkbox.length; ++i)
                    if (checkbox[i].value)
                        removeFromShoppingCard(i)
                loadingCheckout.value = false
            }, 3000)
            context.emit('checkout')
        }

        const removeFromShoppingCard = i => {
            products.value.splice(i, 1)
        }

        return {
            //data
            num, checkbox, products, loadingProducts, noneProduct, loadingCheckout, checkoutPercentage,

            //methods
            init, checkout, getCoverImageList, getThumbnail, removeFromShoppingCard,
        }
    },
    emits: ['checkout']
})
</script>

<style scoped>

</style>