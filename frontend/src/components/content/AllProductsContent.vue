<template>
    <el-row style="margin-top: 4rem" type="flex" justify="center">
        <el-col v-for="p in products" :span="4">
            <el-card shadow="hover">
                <el-image :src="p.imageUrl" fit="contain">
                    <template #placeholder>
                        <div>加载中...</div>
                    </template>
                </el-image>
                <div style="padding:2rem;">
                    {{p.name}}
                    <br>
                    <br>
                    <el-button type="primary" round>点击查看</el-button>
                </div>
            </el-card>
        </el-col>
    </el-row>
</template>

<script>
import URL from '../../js/URL'
import axios from 'axios'

export default {
    name: "AllProductsContent",
    data() {
        return {
            isCollapse: true,
            products:[],
        }
    },
    mounted(){
        axios.get(URL.product).then(res=>{
            if(res.data === 2002)
            {
                axios.get(URL.productData+res.data).then(res=>{
                    console.log(this.products)
                    this.products = res.data
                    console.log(this.products)
                })
            }
            else
            {
                alert('加载商品列表失败')
            }
        })
    },
    methods: {
    }
}
</script>

<style scoped>
</style>