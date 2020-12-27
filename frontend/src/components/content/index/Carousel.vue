<template>
    <el-row justify="center" type="flex">
        <el-col>
            <el-carousel :interval="5000" height="400px" v-if="windowWidth < 992">
                <el-carousel-item v-for="item in 4" :key="item">
                    <el-image v-loading="loadingImage" src="/banner.jpg"></el-image>
                </el-carousel-item>
            </el-carousel>
            <el-carousel :interval="5000" height="400px" v-if="windowWidth >= 992" type="card">
                <el-carousel-item v-for="item in 4" :key="item">
                    <el-image v-loading="loadingImage" src="/banner.jpg"></el-image>
                </el-carousel-item>
            </el-carousel>
        </el-col>
    </el-row>
</template>

<script>
export default {
    name: "Carousel",
    data(){
        return{
            windowWidth:document.documentElement.clientWidth,
            loadingImage:false
        }
    },
    mounted(){
        const that = this
        window.onresize = () =>{
            return (()=>{
                that.windowWidth = document.documentElement.clientWidth
            })()
        }
    },
    methods:{
    }
}
</script>

<style scoped>
</style>