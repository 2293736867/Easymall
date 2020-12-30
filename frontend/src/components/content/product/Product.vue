<template>
    <ProductFirstPart></ProductFirstPart>
    <ProductSecondPart style="margin-top: 3rem"></ProductSecondPart>
    <ProductThirdPart style="margin-top: 3rem"></ProductThirdPart>
</template>

<script>

import ProductFirstPart from "./ProductFirstPart.vue";
import ProductSecondPart from "./ProductSecondPart.vue";
import ProductThirdPart from "./ProductThirdPart.vue";
export default {
    name: "Product",
    components: {ProductThirdPart, ProductSecondPart, ProductFirstPart},
}
</script>

<style scoped>
</style>