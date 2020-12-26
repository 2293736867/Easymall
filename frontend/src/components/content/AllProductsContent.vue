<template>
    <el-container>
        <el-aside style="padding-top: 1rem;" width="100px">
            <side-bar></side-bar>
        </el-aside>
        <el-main>
            <el-row>
                <el-col>
                    <el-form :inline="true" style="margin-top: -1rem">
                        <el-form-item>
                            <el-dropdown>
                                <el-button type="primary">分类
                                </el-button>
                                <template #dropdown>
                                    <el-dropdown-menu>
                                        <el-dropdown-item v-for="item in categories" :key="item.value">{{item.label}}</el-dropdown-item>
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
                            <el-tooltip content="最低价格" placement="top" effect="light">
                                <el-input-number v-model="minPrice" :min="1">
                                </el-input-number>
                            </el-tooltip>
                        </el-form-item>
                        <el-form-item>
                            <el-tooltip content="最高价格" placement="top" effect="light">
                                <el-input-number v-model="maxPrice" :min="1" :max="300">
                                </el-input-number>
                            </el-tooltip>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary">搜索</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
            <el-row justify="center" type="flex">
                <el-col v-for="p in products" :span="4">
                    <el-card shadow="hover" v-loading="loading" element-loading-text="拼命加载中">
                        <el-image :src="p.imageUrl" fit="contain">
                            <template #placeholder>
                                <div>加载中...</div>
                            </template>
                        </el-image>
                        <div style="padding:2rem;">
                            {{ p.name }}
                            <br>
                            <br>
                            <el-button round type="primary" @click="toProduct">点击查看</el-button>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
import URL from '../../store/gloabl'
import axios from 'axios'
import router from "../../js/router";
import SideBar from "../other/SideBar.vue";

export default {
    name: "AllProductsContent",
    components: {SideBar},
    data() {
        return {
            isCollapse: true,
            products: [],
            loading: true,
            categories: [{
                value: '1',
                label: '手机数码'
            }, {
                value: '2',
                label: '电脑平板'
            }, {
                value: '3',
                label: '家用电器'
            }, {
                value: '4',
                label: '汽车用品'
            }, {
                value: '5',
                label: '食品材料'
            }, {
                value: '6',
                label: '图书杂志'
            }, {
                value: '7',
                label: '服装服饰'
            }, {
                value: '8',
                label: '理财产品'
            }],
            minPrice: 1,
            maxPrice: 1,
        }
    },
    mounted() {
        axios.get(URL.product).then(res => {
            if (res.data === 2002) {
                axios.get(URL.productData + res.data).then(res => {
                    this.products = res.data
                    this.loading = false
                })
            } else {
                alert('加载商品列表失败')
            }
        })
    },
    methods: {
        toProduct() {
            router.push({
                path: '/product'
            })
        }
    }
}
</script>

<style scoped>
</style>