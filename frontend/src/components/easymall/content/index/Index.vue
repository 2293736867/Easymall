<template>
    <el-row justify="center" type="flex" style="margin-top: 2rem">
        <el-col>
            <el-carousel :interval="5000" height="400px" v-if="windowWidth < 1400">
                <el-carousel-item v-for="item in 5" :key="item">
                    <el-image v-loading="loadingImage" element-loading-text="拼命加载中" :src="getBanner(item)"></el-image>
                </el-carousel-item>
            </el-carousel>
            <el-carousel :interval="5000" height="400px" v-else type="card">
                <el-carousel-item v-for="item in 5" :key="item">
                    <el-image v-loading="loadingImage" element-loading-text="拼命加载中" :src="getBanner(item)"></el-image>
                </el-carousel-item>
            </el-carousel>
        </el-col>
    </el-row>
</template>

<script>
export default {
    name: "Index",
    data() {
        return {
            windowWidth: document.documentElement.clientWidth,
            loadingImage: false
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
        getBanner(i){
            return "/banner/"+i+".png"
        }
    }
}
</script>

<style scoped>
</style>