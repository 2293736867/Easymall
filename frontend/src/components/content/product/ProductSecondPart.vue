<template>
    <el-row justify="center" type="flex">
        <el-col :span="16">
            <el-row v-for="i in tableData">
                <el-col>
                    <el-card shadow="hover">
                        <el-tag>{{ i.k }}</el-tag>
                        <span style="color:#606266;margin-left: 0.5rem">{{ i.v }}</span>
                    </el-card>
                </el-col>
            </el-row>
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
            tableData: [],
        }
    },
    mounted() {
        this.product = JSON.parse(localStorage.getItem('product'))
        this.description = JSON.parse(this.product.description)
        for (var i in this.description) {
            if (this.description.hasOwnProperty(i)) {
                this.tableData.push({
                    'k': i,
                    'v': this.description[i]
                })
            }
        }
    }
}
</script>

<style scoped>

</style>