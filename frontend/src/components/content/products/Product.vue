<template>
    <el-row justify="center" type="flex">
        <el-col :span="6">
            <el-carousel height="430px" :autoplay="true" ref="aaa">
                <el-carousel-item v-for="item in 5" :key="item">
                    <el-image v-loading="loadingImage" :src="coverImage(item)">
                        <template #error>
                            <el-image src="/DefaultProduct.png"></el-image>
                        </template>
                    </el-image>
                </el-carousel-item>
            </el-carousel>
            <div>
                <el-image v-for="i in product.coverImagesSize" :src="product.thumbnailImageBaseUrl + i" style="padding: 0.3rem;margin-top: 1rem;margin-left: 0.5rem;margin-right: 0.5rem" @mouseover="ccc" >
                </el-image>
            </div>
        </el-col>
        <el-col :span="8">
            <el-divider></el-divider>
            <h2>商品名称</h2>
            <el-divider></el-divider>
            <el-table style="margin-bottom: 2rem">
                <el-table-column label="价格" prop="price">
                    {{ product.price }}
                </el-table-column>
                <el-table-column label="运费" prop="freight">
                    111
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
            }],
            id: '',
            product: '',
        }
    },
    computed:{
    },
    created() {
        this.id = this.$route.params.id
        this.product = JSON.parse(localStorage.getItem('product'))
    },
    methods: {
        coverImage(item){
            this.$emit('setActiveItem',2)
            // this.$refs.carousel.setActiveItem(1)
            this.loadingImage = false
            // console.log(item)
            // console.log(typeof (item))
            return this.product.coverImageBaseUrl + 2
        },
        changeImage(a,b)
        {
            console.log(a)
            console.log(b)
        },
        ccc(event)
        {
            console.log(event.target)
            console.log('ccc')
        }
    }
}
</script>

<style scoped>

</style>