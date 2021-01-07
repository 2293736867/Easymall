<template>
    <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
            label="Id"
            prop="id"
            width="180">
        </el-table-column>
        <el-table-column
            label="用户名"
            prop="username"
            width="180">
        </el-table-column>
        <el-table-column
            label="昵称"
            prop="nickname">
        </el-table-column>
        <el-table-column
            label="邮箱"
            prop="email">
        </el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-tooltip content="编辑" effect="light" placement="top">
                    <el-button circle icon="el-icon-edit" size="medium" type="primary" @click="editUser(scope.row)">
                    </el-button>
                </el-tooltip>
                <el-popconfirm
                    cancelButtonText='不用了'
                    confirmButtonText='好的'
                    icon="el-icon-info"
                    iconColor="red"
                    title="这是一段内容确定删除吗？"
                >
                    <template #reference>
                        <el-button circle icon="el-icon-close" size="medium" type="danger"
                                   @click="deleteUser(scope.$index, scope.row)">
                        </el-button>
                    </template>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>

    <AdminUserModifyDrawer ref="drawer" v-model="modifyDrawer"></AdminUserModifyDrawer>
</template>

<script>
import AdminUserModifyDrawer from "./AdminUserModifyDrawer.vue";

export default {
    name: "AdminUserManagementTabPane",
    components: {AdminUserModifyDrawer},
    data() {
        return {
            tableData: [{
                id: '2016-05-02',
                username: '王小虎',
                nickname: '上海市普陀区金沙江路 1518 弄',
                email: ''
            }, {
                id: '2016-05-04',
                username: '王小虎',
                nickname: '',
                email: '上海市普陀区金沙江路 1517 弄'
            }, {
                id: '2016-05-01',
                username: '王小虎',
                nickname: '',
                email: '上海市普陀区金沙江路 1519 弄'
            }, {
                id: '2016-05-03',
                username: '王小虎',
                nickname: '',
                email: '上海市普陀区金沙江路 1516 弄'
            }],
            modifyDrawer: false
        }
    },
    methods: {
        editUser(row) {
            this.$refs.drawer.init(row)
            this.modifyDrawer = true
        },
        deleteUser(index, row) {

        },
    },
}
</script>

<style scoped>

</style>