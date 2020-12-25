<template>
    <el-container>
        <el-header>
            <Header></Header>
        </el-header>
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
import Header from "./header/Header.vue";
import Carousel from "./content/Carousel.vue";

export default {
    name: "Index",
    components: {Carousel, Header}
}
</script>

<style scoped>
</style>