<template>
    <el-row justify="center" type="flex">
        <el-col :span="6">
            <el-carousel>
                <el-carousel-item v-for="item in 4">
                    <el-image v-loading="loadingImage" src="/1.jpg">
                        <template #error>
                            <div class="image-slot">
                                <i class="el-icon-picture-outline"></i>
                            </div>
                        </template>
                    </el-image>
                </el-carousel-item>
            </el-carousel>
        </el-col>
        <el-col :span="8">
            <el-divider></el-divider>
            <h2>商品名称</h2>
            <el-divider></el-divider>
            <el-table :data="tableData" style="margin-bottom: 2rem">
                <el-table-column label="价格" prop="price">
                </el-table-column>
                <el-table-column label="运费" prop="freight">
                </el-table-column>
            </el-table>
            <el-input-number :max="2" :min="1"></el-input-number>
            <el-divider direction="vertical"></el-divider>
            <el-button type="primary">加入购物车</el-button>
            <el-divider direction="vertical"></el-divider>
            <el-button type="primary">购买</el-button>
            <el-divider></el-divider>
        </el-col>
    </el-row>
</template>

<script>
export default {
    name: "Product",
    data() {
        return {
            loadingImage: true,
            tableData: [{
                price: '11',
                freight: '22',
                nums: '33',
            }],
        }
    },
    mounted() {
        setInterval(() => {
            this.loadingImage = false
        }, 2000)
    },
    methods: {}
}
</script>

<style scoped>

</style>