<template>
    <el-empty v-if="none" :description="noneText"></el-empty>
    <el-row v-else>
        <el-col :span="24">
            <el-table v-loading="loadingTable" :data="users">
                <el-table-column
                    label="Id"
                    prop="id">
                </el-table-column>
                <el-table-column
                    label="用户名"
                    prop="username">
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
                            <el-button circle icon="el-icon-edit" size="medium" type="primary"
                                       @click="editUser(scope.row)">
                            </el-button>
                        </el-tooltip>
                        <el-popconfirm
                            cancelButtonText='否'
                            confirmButtonText='是'
                            icon="el-icon-error"
                            iconColor="red"
                            title="确定删除该用户吗？"
                            @confirm="deleteUser(scope.row)"
                        >
                            <template #reference>
                                <el-button circle icon="el-icon-close" size="medium" type="danger">
                                </el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </el-col>
        <el-col>
            <el-row justify="end" type="flex" style="margin-top: 1.5rem">
                <el-col :span="1">
                    <el-tooltip placement="left" content="添加用户" effect="light">
                        <el-button type="primary" circle icon="el-icon-plus" size="medium" @click="showAddDrawer"></el-button>
                    </el-tooltip>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
    <AdminUserModifyDrawer ref="modifyDrawer" v-model="modifyDrawer" @success="modifyTable"></AdminUserModifyDrawer>
    <AdminUserAddDrawer v-model="addDrawer" @success="addTable"></AdminUserAddDrawer>
</template>

<script>
import AdminUserModifyDrawer from "./AdminUserModifyDrawer.vue";
import axios from "axios";
import URL from "../../../../../js/constant/URL";
import AdminUserAddDrawer from "./AdminUserAddDrawer.vue";

export default {
    name: "AdminUserManagementTabPane",
    components: {AdminUserAddDrawer, AdminUserModifyDrawer},
    data() {
        return {
            users: [],
            modifyDrawer: false,
            addDrawer:false,
            loadingTable: true,
            none: false,
            noneText: '用户列表为空'
        }
    },
    methods: {
        editUser(row) {
            this.$refs.modifyDrawer.init(row)
            this.modifyDrawer = true
        },
        deleteUser(row) {
            axios.delete(URL.userDelete + row.id).then(res => {
                let code = parseInt(res.data.code)
                if (code === 100300) {
                    this.users.forEach((item, i) => {
                        if (item.id === row.id)
                            this.users.splice(i, 1)
                    })
                    this.$message.success('删除成功')
                } else {
                    this.$message.error('未知原因删除失败')
                }
            })
        },
        modifyTable(u) {
            let target = this.users.find(item => {
                return item.id === u.id
            })
            target.username = u.username
            target.email = u.email
            target.nickname = u.nickname
        },
        addTable(u){
            this.users.push(u)
        },
        showAddDrawer(){
            this.addDrawer = true
        }
    },
    mounted() {
        axios.get(URL.userGetAll).then(res => {
            let code = parseInt(res.data.code)
            if (code === 100105) {
                this.users = res.data.data
                this.loadingTable = false
            } else {
                this.none = true
                if (code !== 100106) {
                    this.noneText = '未知原因加载失败'
                }
            }
        })
    }
}
</script>

<style scoped>

</style>