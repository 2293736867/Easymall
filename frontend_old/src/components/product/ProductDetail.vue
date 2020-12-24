<template>
    <div>
        <Header></Header>
        <b-container class="mt-4">
            <b-row>
                <b-col lg="6" md="12">
                    <b-card>
                        <b-carousel
                            fade
                            indicators
                            img-width="1024"
                            img-height="480"
                        >
                            <b-carousel-slide
                                :img-src="this.img1"
                            ></b-carousel-slide>
                            <b-carousel-slide
                                :img-src="this.img2"
                            ></b-carousel-slide>
                            <b-carousel-slide
                                :img-src="img3"
                            ></b-carousel-slide>
                        </b-carousel>
                        <template v-slot:footer>
                            <b-button variant="primary">加入购物车</b-button>
                        </template>
                    </b-card>
                </b-col>
                <b-col lg="6" md="12">
                    <b-card header="Title">
                        <b-card-text>
                            <b-list-group>
                                <b-list-group-item>Easymall价:{{p.price}}</b-list-group-item>
                                <b-list-group-item>运费:满100免运费</b-list-group-item>
                                <b-list-group-item>服务:由Easymall负责发货，并提供售后服务</b-list-group-item>
                                <b-list-group-item>
                                    <b-input-group prepend="购买数量">
                                        <b-form-input type="number" min="0"></b-form-input>
                                    </b-input-group>
                                </b-list-group-item>
                            </b-list-group>
                        </b-card-text>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
import Header from "@/components/Header";
export default {
    name: "ProductDetail",
    components: {Header},
    data(){
        return {
            id:'',
            img1:'',
            img2:'',
            img3:'',
            p:'',
        }
    },
    created() {
        this.id = this.$route.params['id']
        this.p = JSON.parse(localStorage.getItem('product'))
        this.img1 = this.img2 = this.img3 = this.p.imageUrl
    },
    methods:{

    }
}
</script>

<style scoped>

</style>