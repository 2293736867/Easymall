<template>
        <div>
            <ProductFirstPart></ProductFirstPart>
        </div>
        <div style="margin-top: 3rem">
            <el-divider></el-divider>
            <ProductSecondPart></ProductSecondPart>
        </div>
        <div style="margin-top: 3rem">
            <el-divider></el-divider>
            <ProductThirdPart></ProductThirdPart>
        </div>
</template>

<script>
import ProductFirstPart from "./ProductFirstPart.vue";
import ProductSecondPart from "./ProductSecondPart.vue";
import ProductThirdPart from "./ProductThirdPart.vue";
import {defineComponent} from 'vue'

export default defineComponent({
    name: "Product",
    components: {ProductThirdPart, ProductSecondPart, ProductFirstPart},
})
</script>

<style scoped>
</style>