<template>
    <div>
        <b-card img-top img-src="../assets/1.jpg" style="max-width: 20rem" no-body>
<!--            <b-card-header >-->
<!--                <b-img src="../assets/1.jpg"></b-img>-->
<!--            </b-card-header>-->
            <template #header>
                <h4 class="mb-0">Header</h4>
            </template>
<!--            <b-card-body>-->
<!--                <b-card-title>Title</b-card-title>-->
<!--                <b-card-sub-title class="mb-2">Sub title</b-card-sub-title>-->
<!--                <b-card-text>Test</b-card-text>-->
<!--            </b-card-body>-->
            <b-list-group flush>
                <b-list-group-item>Price</b-list-group-item>
                <b-list-group-item>xxxx人评价</b-list-group-item>
            </b-list-group>
            <b-card-footer>
                <b-button variant="success">加入购物车</b-button>
            </b-card-footer>
        </b-card>
    </div>
</template>

<script>
export default {
name: "ProductCard"
}
</script>

<style scoped>
</style>