<template>
    <div class="block">
        <el-carousel height="400px">
            <el-carousel-item v-for="item in 4" :key="item">
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
export default {
    name: "Index"
}
</script>

<style scoped>
    .el-carousel__item:nth-child(n){
        background-image: url("../assets/banner.jpg");
    }
</style>