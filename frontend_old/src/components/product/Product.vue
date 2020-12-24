<template>
    <div>
        <Header></Header>
        <br>
        <b-container fluid="">
            <b-row>
                <b-col cols="10" offset="1">
                    <ProductHeader></ProductHeader>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col offset="1" xl="10">
                    <b-row>
                        <b-col v-for="p in products[0]" v-bind:key="p.id" class="mt-4" lg="3" md="4" sm="6">
                            <b-card :img-src="p.imageUrl" img-top no-body style="max-width: 20rem">
                                <template #header>
                                    <h4 class="mb-0" @click="toProduct(p.id,p)">{{ p.name }}</h4>
                                </template>
                                <b-list-group flush>
                                    <b-list-group-item>{{ p.price }}</b-list-group-item>
                                    <b-list-group-item>xxxx人评价</b-list-group-item>
                                </b-list-group>
                                <b-card-footer>
                                    <b-button variant="success">加入购物车</b-button>
                                </b-card-footer>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-col>
            </b-row>
        </b-container>

        <b-modal id="productMessage">{{ message }}</b-modal>
    </div>
</template>

<script>
import ProductHeader from "@/components/product/ProductHeader";
import Header from "@/components/Header";
import axios from "axios";
import URL from "@/js/URL";
import router from "@/js/router";

export default {
    name: "Product",
    components: {Header, ProductHeader},
    data() {
        return {
            message: '',
            products: [],
        }
    },
    created() {
        var products = this.products
        axios.get(URL.product).then(function (res) {
            if (res.data === 2002) {
                axios.get(URL.productData + res.data).then(res => {
                    products.push(res.data)
                })
            } else {
                this.message = '获取商品失败'
                this.$bvModal.show('productMessage')
            }
        })
    },
    methods: {
        toProduct(id,p) {
            localStorage.setItem('product',JSON.stringify(p))
            router.push({
                path: '/product/' + id
            })
        }
    }
}
</script>

<style scoped>

</style>