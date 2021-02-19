<template>
    <el-row justify="center" style="margin-top: 2rem" type="flex">
        <el-col>
            <el-carousel :interval="5000" height="400px" type="card">
                <el-carousel-item v-for="item in 5" :key="item">
                    <el-image v-loading="loadingImage" :src="getBanner(item)" element-loading-text="拼命加载中"></el-image>
                </el-carousel-item>
            </el-carousel>
        </el-col>
    </el-row>
    <el-row v-show="products.length !== 0" justify="center" type="flex" style="margin-top: 2rem">
        <el-col v-for="p in products" :span="4">
            <el-card v-loading="loading" element-loading-text="拼命加载中" shadow="hover">
                <el-image :src="p.coverImageBaseUrl+1" fit="contain">
                    <template #placeholder>
                        <div>加载中...</div>
                    </template>
                    <template #error>
                        <el-image src="/default/Product.png"></el-image>
                    </template>
                </el-image>
                <div style="padding:2rem;">
                    {{ p.name }}
                    <br><br>
                    <el-button round type="primary" @click="toProduct(p.id)">点击查看</el-button>
                </div>
            </el-card>
        </el-col>
    </el-row>
</template>

<script>
import axios from 'axios'
import URL from "../../../../js/constant/URL";
import router from "../../../../js/router/Router";
import {defineComponent,ref} from 'vue'

export default defineComponent({
    name: "Index",
    setup(){
        const loadingImage = ref(false)
        const products = ref([])

        axios.get(URL.productGetByCategory).then(res => {
            this.products = res.data.data
            if (parseInt(res.data.code) === 110104 || parseInt(res.data.code) === 110106) {
                this.none = false
                this.loading = false
                this.products.splice(6)
            } else if (parseInt(res.data.code) === 110105 || parseInt(res.data.code) === 110107) {
                this.none = true
                this.$message.warning('商品列表为空')
            } else {
                this.none = true
                this.$message.warning('未知原因加载商品失败')
            }
        })

        const getBanner = i => {
            return "/banner/" + i + ".png"
        }

        const toProduct = id => {
            localStorage.setItem('product', JSON.stringify(this.products.find(p => {
                return p.id === id
            })))
            router.push({
                path: '/product/' + id
            })
        }

        return {
            //data
            loadingImage, products,

            //methods
            getBanner,toProduct
        }
    }
})
</script>

<style scoped>
</style>