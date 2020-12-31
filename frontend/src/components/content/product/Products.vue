<template>
    <el-container>
        <el-aside v-if="isUserSignIn" style="padding-top: 1rem;position: absolute;left: 0" width="100px;">
            <side-bar></side-bar>
        </el-aside>
        <el-main>
            <el-row v-show="!none">
                <el-col>
                    <el-form :inline="true" style="margin-top: -1rem">
                        <el-form-item>
                            <el-dropdown>
                                <el-button type="primary">分类
                                </el-button>
                                <template #dropdown>
                                    <el-dropdown-menu>
                                        <el-dropdown-item v-for="item in categories" :key="item.index">
                                            {{ item.value }}
                                        </el-dropdown-item>
                                    </el-dropdown-menu>
                                </template>
                            </el-dropdown>
                        </el-form-item>
                        <el-form-item>
                            <el-input placeholder="按商品名搜索">
                                <template #append>
                                    <i class="el-icon-search"></i>
                                </template>
                            </el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-tooltip content="最低价格" effect="light" placement="top">
                                <el-input-number v-model="minPrice" :min="1">
                                </el-input-number>
                            </el-tooltip>
                        </el-form-item>
                        <el-form-item>
                            <el-tooltip content="最高价格" effect="light" placement="top">
                                <el-input-number v-model="maxPrice" :max="300" :min="1">
                                </el-input-number>
                            </el-tooltip>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary">搜索</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
            <el-row v-show="!none" justify="center" type="flex">
                <el-col v-for="p in products" :span="4">
                    <el-card v-loading="loading" element-loading-text="拼命加载中" shadow="hover">
                        <el-image :src="p.coverImageBaseUrl+1" fit="contain">
                            <template #placeholder>
                                <div>加载中...</div>
                            </template>
                            <template #error>
                                <el-image src="/DefaultProduct.png"></el-image>
                                <div>加载失败</div>
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
            <el-row>
                <el-col v-show="none">
                    <el-image src="/DefaultProduct.png"></el-image>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
import URL from "../../../js/constant/URL";
import axios from 'axios'
import router from "../../../js/router/router";
import SideBar from "../other/SideBar.vue";
import Categories from "../../../js/constant/Categories";

export default {
    name: "Products",
    components: {SideBar},
    data() {
        return {
            isCollapse: true,
            products: [],
            loading: true,
            categories: Categories,
            minPrice: 1,
            maxPrice: 1,
            none: false,
        }
    },
    computed: {
        isUserSignIn() {
            return this.$store.getters.isUserSignIn
        },
        categoryId() {
            return this.$store.getters.categoryId
        }
    },
    watch: {
        categoryId(id) {
            this.loadProductByCategory(id)
        },
    },
    mounted() {
        this.loadProductByCategory(this.categoryId)
    },
    methods: {
        toProduct(id) {
            localStorage.setItem('product', JSON.stringify(this.products.find(p => {
                return p.id === id
            })))
            router.push({
                path: '/product/' + id
            })
        },
        loadProductByCategory(id) {
            id = parseInt(id)
            if (id >= 1 && id <= Categories.length) {
                var allProducts = (id === 1)
                axios.get(URL.productGetByCategory + (allProducts ? "" : Categories[id - 1].value)).then(res => {
                    if (res.data === allProducts ? 2012 : 2010) {
                        axios.get(URL.productData + res.data).then(res => {
                            this.products = res.data
                            if (this.products.length === 0) {
                                this.$message.warning('商品列表为空')
                                this.none = true
                            } else {
                                this.none = false
                                this.loading = false
                            }
                        })
                    } else {
                        this.none = true
                        this.$message.warning('未知原因加载商品失败')
                    }
                })
            }
        },
    }
}
</script>

<style scoped>
</style>