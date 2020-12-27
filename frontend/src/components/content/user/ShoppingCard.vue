<template>
    <el-table
        ref="multipleTable"
        :data="tableData"
        style="width: 100%"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            label="日期"
            width="120">
            <template #default="scope">{{ scope.row.date }}</template>
        </el-table-column>
        <el-table-column
            label="姓名"
            prop="name"
            width="120">
        </el-table-column>
        <el-table-column
            label="地址"
            prop="address"
            show-overflow-tooltip>
        </el-table-column>
    </el-table>
</template>

<script>
export default {
    name: "ShoppingCard",
    data() {
        return {
            tableData:[{
                name:'111',
                address:'ccc',
                date:'2020-10-23'
            }]
        }
    },
}
</script>

<style scoped>

</style>