<template>
    <el-row justify="center" type="flex">
        <el-col :span="6">
            <el-carousel ref="productDetailCarousel" :autoplay="false" height="430px">
                <el-carousel-item v-for="item in product.coverImagesSize" :key="item">
                    <el-image :src="product.coverImageBaseUrl+item" v-loading="loadingCoverImage">
                        <template #error>
                            <el-image src="/DefaultProduct.png"></el-image>
                        </template>
                    </el-image>
                </el-carousel-item>
            </el-carousel>
            <div>
                <el-image v-for="i in product.coverImagesSize" v-loading="loadingThumbnailImage"
                      :class="{'thumbnail-mouse-hover':showBorder[i],'thumbnail-mouse-leave':!showBorder[i]}"
                      :src="product.thumbnailImageBaseUrl+i" class="thumbnail"
                      @mouseleave="removeBorderOnLeave(i)" @mouseover="showBorderOnHover(i)">
                </el-image>
            </div>
        </el-col>
        <el-col :span="8" style="margin-left: 0.5rem">
            <el-divider></el-divider>
            <h2>
                {{ product.name }}
                <el-tag>{{product.category}}</el-tag>
            </h2>
            <el-divider></el-divider>
            <el-table :data="tableData" style="margin-bottom: 2rem;text-align: center">
                <el-table-column label="价格" prop="price"></el-table-column>
                <el-table-column label="运费" prop="freight"></el-table-column>
                <el-table-column label="剩余库存" prop="num"></el-table-column>
            </el-table>
            <el-tooltip content="购买数量" placement="left">
                <el-input-number v-model="affordNum" :max="product.num" :min="1">
                </el-input-number>
            </el-tooltip>
            <el-divider direction="vertical"></el-divider>
            <el-button icon="el-icon-shopping-cart-1" type="primary">
                加入购物车
            </el-button>
            <el-divider direction="vertical"></el-divider>
            <el-button icon="el-icon-shopping-bag-1" type="primary">
                购买
            </el-button>
            <el-divider></el-divider>
        </el-col>
    </el-row>
</template>

<script>
export default {
    name: "ProductFirstPart",
    data() {
        return {
            product: '',
            tableData: [{
                price: '',
                freight: '',
                num: '',
            }],
            loadingCoverImage: false,
            loadingThumbnailImage: false,
            showBorder: [],
            affordNum: 1,
        }
    },
    created() {
        this.product = JSON.parse(localStorage.getItem('product'))
        this.tableData[0].price = this.product.price
        this.tableData[0].freight = this.product.freight
        this.tableData[0].num = this.product.num
        for (var i = 0; i < this.product.coverImagesSize; ++i) {
            this.showBorder.push(false)
        }
    },
    methods: {
        showBorderOnHover(i) {
            this.$refs.productDetailCarousel.setActiveItem(i - 1)
            this.showBorder[i] = true
        },
        removeBorderOnLeave(i) {
            this.showBorder[i] = false
        },
    }
}
</script>

<style scoped>
.thumbnail-mouse-hover {
    border: 1px solid black;
}

.thumbnail-mouse-leave {
    border: 1px solid white;
}

.thumbnail {
    padding: 0.3rem;
    margin-top: 1rem;
    margin-left: 0.5rem;
    margin-right: 0.5rem;
}
</style>