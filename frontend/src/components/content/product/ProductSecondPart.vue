<template>
    <el-row justify="center" type="flex">
        <el-col :span="16">
            <el-table :data="tableData">
                <el-table-column prop="key" label="文字">
                </el-table-column>
                <el-table-column prop="value" label="描述"></el-table-column>
            </el-table>
        </el-col>
    </el-row>
</template>
<script>
export default {
    name: "ProductSecondPart",
    data() {
        return {
            product: '',
            description: '',
            tableData:[],
        }
    },
    mounted() {
        this.product = JSON.parse(localStorage.getItem('product'))
        this.description = JSON.parse(this.product.description)
        for(var i in this.description)
        {
            if(this.description.hasOwnProperty(i))
            {
                this.tableData.push({
                    'key':i,
                    'value':this.description[i]
                })
            }
        }
    }
}
</script>

<style scoped>

</style>