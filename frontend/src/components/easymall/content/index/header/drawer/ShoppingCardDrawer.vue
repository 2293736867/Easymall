<template>
    <el-drawer destroy-on-close direction="rtl" size="50%" title="购物车">
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
                                    <el-image :preview-src-list="getCoverImageList(p)" :src="getThumbnail(p)" fit="contain"
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
                                {{ p.price * num[i]}}
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
                        <el-progress style="margin-top: 0.7rem;margin-left: 2.2rem" v-if="checkoutPercentage !== 0" :text-inside="true" :stroke-width="14" :percentage="checkoutPercentage"></el-progress>
                    </el-col>
                    <el-col :span="4">
                        <el-button type="primary" element-loading-background="rgba(0, 0, 0, 0.6)" v-loading="loadingCheckout" @click="checkout">结算</el-button>
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
            num: [],
            checkbox: [],
            products: [],
            loadingProducts: true,
            none: false,
            loadingCheckout:false,
            checkoutPercentage:0,
        }
    },
    methods: {
        init() {
            axios.get(URL.shoppingCardGet,{
                headers:{
                    userToken:localStorage.getItem('userToken')
                }
            }).then(res=>{
                let code = parseInt(res.data.code)
                if(code === 120100) {
                    if(res.data.data.length === 0){
                        this.none = true
                    }
                    else
                    {
                        this.shoppingCardNum = res.data.data.length
                        this.products = res.data.data
                        for(let i=0;i<this.products.length;++i)
                        {
                            this.checkbox.push(false)
                            this.num[i] = 1
                        }
                        this.loadingProducts = false
                        this.$store.commit('set',{
                            k:'shoppingCardProducts',
                            v:JSON.stringify(res.data.data)
                        })
                    }
                }else if(code === 120101){
                    this.none = true
                }
            })
        },
        getThumbnail(p){
            return p.thumbnailImageBaseUrl+"1"
        },
        getCoverImageList(p){
            let list = []
            for(let i=0;i<p.coverImagesSize;++i)
                list.push(p.coverImageBaseUrl+(i+1))
            return list
        },
        checkout() {
            this.loadingCheckout = true
            let interval = setInterval(_=>{
                ++this.checkoutPercentage
                if(this.checkoutPercentage === 100)
                {
                    clearInterval(interval)
                    this.checkoutPercentage = 0
                }
            },30)
            setTimeout(_=>{
                this.$alert('购买成功');
                for(let i = 0;i<this.checkbox.length;++i)
                    if(this.checkbox[i])
                        this.removeFromShoppingCard(i)
                this.loadingCheckout = false
            },3000)
            this.$emit('checkout')
        },
        removeFromShoppingCard(i){
            this.products.splice(i,1)
        }
    },
    emits: ['checkout']
}
</script>

<style scoped>

</style>