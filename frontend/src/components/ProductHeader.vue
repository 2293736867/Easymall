<template>
    <div>
        <b-container>
            <b-row>
                <b-col cols="2">
                    <b-input-group prepend="商品名">
                        <b-form-input></b-form-input>
                    </b-input-group>
                </b-col>
                <b-col cols="1">
                    <b-dropdown text="商品种类" variant="primary">
                        <b-dropdown-item>电子数码</b-dropdown-item>
                        <b-dropdown-item>日用百货</b-dropdown-item>
                        <b-dropdown-item>家用电器</b-dropdown-item>
                        <b-dropdown-item>图书杂志</b-dropdown-item>
                        <b-dropdown-item>服装装饰</b-dropdown-item>
                        <b-dropdown-item>床上用品</b-dropdown-item>
                    </b-dropdown>
                </b-col>
                <b-col offset="1" cols="7">
                    <b-row>
                        <b-col>
                            <b-input-group prepend="价格区间"></b-input-group>
                        </b-col>
                        <b-col>
                            <b-input-group prepend="最低">
                                <b-form-input type="number"></b-form-input>
                            </b-input-group>
                        </b-col>
                        <b-col>
                            <b-input-group prepend="最高">
                                <b-form-input type="number"></b-form-input>
                            </b-input-group>
                        </b-col>
                        <b-col>
                            <b-input-group-append>
                                <b-button variant="success">查询</b-button>
                            </b-input-group-append>
                        </b-col>
                    </b-row>
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