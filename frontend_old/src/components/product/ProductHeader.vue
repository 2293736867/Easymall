<template>
    <div>
        <b-container fluid="">
            <b-row>
                <b-col cols="3">
                    <div>
                        <b-input-group prepend="商品名">
                            <b-form-input></b-form-input>
                            <b-dropdown class="ml-2" text="商品种类" variant="primary">
                                <b-dropdown-item>电子数码</b-dropdown-item>
                                <b-dropdown-item>日用百货</b-dropdown-item>
                                <b-dropdown-item>家用电器</b-dropdown-item>
                                <b-dropdown-item>图书杂志</b-dropdown-item>
                                <b-dropdown-item>服装装饰</b-dropdown-item>
                                <b-dropdown-item>床上用品</b-dropdown-item>
                            </b-dropdown>
                        </b-input-group>
                    </div>
                </b-col>
                <b-col cols="6" offset="2">
                    <b-input-group prepend="价格区间">
                        <b-button class="ml-2" variant="info">最低</b-button>
                        <b-form-input type="number" class="mr-2"></b-form-input>
                        <b-button variant="info">最高</b-button>
                        <b-form-input type="number"></b-form-input>
                    </b-input-group>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
export default {
    name: "Product"
}
</script>

<style scoped>

</style>