<template>
    <el-row justify="center" type="flex">
        <el-col >
            <el-carousel :interval="5000" height="400px" :type="cardOnLgScreen">
                <el-carousel-item v-for="item in 4" :key="item">
                    <el-image src="/banner.jpg"></el-image>
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
            windowWidth:document.documentElement.clientWidth
        }
    },
    mounted(){
        var that = this
        window.onresize = () =>{
            return (()=>{
                that.windowWidth = document.documentElement.clientWidth
            })
        }
    },
    watch:{
        width(val){
            console.log(val)
            let that = this
            console.log(that.windowWidth)
        }
    },
    computed:{
        cardOnLgScreen(){
            console.log(this.windowWidth)
            return this.windowWidth > 992 ? "card" : ""
        }
    }
}
</script>

<style scoped>
</style>