<template>
    <div>
        <b-container>
            <b-row>
                <b-col>
                    <ProductHeader></ProductHeader>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col>
                    <ProductCard></ProductCard>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
import ProductHeader from "@/components/ProductHeader";
import ProductCard from "@/components/ProductCard";
export default {
    name: "Product",
    components: {ProductCard,ProductHeader},
}
</script>

<style scoped>

</style>