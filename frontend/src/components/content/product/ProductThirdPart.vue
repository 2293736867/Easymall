<template>
    <el-row justify="center" type="flex" v-for="i in detailImagesSize">
        <el-col >
            <el-image :src="product.detailImageBaseUrl+i"></el-image>
        </el-col>
    </el-row>
</template>

<script>
export default {
    name: "ProductThirdPart",
    data(){
        return {
            product:'',
            detailImagesSize:'',
        }
    },
    mounted(){
        this.product = JSON.parse(localStorage.getItem('product'))
        this.detailImagesSize = this.product.detailImagesSize
    },
}
</script>

<style scoped>

</style>