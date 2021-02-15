<template>
    <el-empty v-if="noneUser" :description="noneTipText"></el-empty>
    <el-row v-else>
        <el-col :span="24">
            <el-table v-loading="loading" :data="users">
                <el-table-column label="Id" prop="id"></el-table-column>
                <el-table-column label="用户名" prop="username"></el-table-column>
                <el-table-column label="昵称" prop="nickname"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-tooltip content="编辑" effect="light" placement="top">
                            <el-button circle icon="el-icon-edit" size="medium" type="primary"
                                   @click="modifyUser(scope.row)">
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
            <el-row justify="end" style="margin-top: 1.5rem" type="flex">
                <el-col :span="1">
                    <el-tooltip content="添加用户" effect="light" placement="left">
                        <el-button circle icon="el-icon-plus" size="medium" type="primary"
                                   @click="addUser"></el-button>
                    </el-tooltip>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
    <AdminUserModifyDrawer ref="modifyDrawer" v-model="showModifyDrawer"
                           @success="modifyUserSuccessCallback"></AdminUserModifyDrawer>
    <AdminUserAddDrawer v-model="showAddDrawer" @success="addUserSuccessCallback"></AdminUserAddDrawer>
</template>

<script>
import AdminUserModifyDrawer from "./AdminUserModifyDrawer.vue";
import axios from "axios";
import URL from "../../../../../js/constant/URL";
import AdminUserAddDrawer from "./AdminUserAddDrawer.vue";
import {defineComponent, ref} from 'vue'
import {ElMessage} from 'element-plus'
import Utils from "../../../../../js/utils/Utils";
import UserUtils from "../../../../../js/utils/UserUtils";

export default defineComponent({
    name: "AdminUserManagementTabPane",
    components: {AdminUserAddDrawer, AdminUserModifyDrawer},
    setup() {
        const modifyDrawer = ref(null)
        const showAddDrawer = ref(false)
        const showModifyDrawer = ref(false)
        const users = ref([])
        const loading = ref(true)
        const noneUser = ref(false)
        const noneTipText = ref('用户列表为空')

        axios.get(URL.userGetAll).then(res => {
            if (Utils.responseCodeEquals(res, 100105)) {
                users.value = Utils.getResponseData(res)
                loading.value = false
            } else {
                noneUser.value = true
                if (Utils.responseCodeNotEquals(res, 100106)) {
                    noneTipText.value = '未知原因加载失败'
                }
            }
        })

        const addUser = _ => {
            showAddDrawer.value = true
        }

        const modifyUser = row => {
            modifyDrawer.value.init(row)
            showModifyDrawer.value = true
        }

        const deleteUser = row => {
            axios.delete(URL.userDelete + row.id).then(res => {
                if (Utils.responseCodeEquals(res, 100300)) {
                    users.value.forEach((item, i) => {
                        if (item.id === row.id)
                            users.value.splice(i, 1)
                    })
                    ElMessage.success('删除成功')
                } else {
                    ElMessage.error('未知原因删除失败')
                }
            })
        }

        const modifyUserSuccessCallback = u => {
            let target = users.value.find(item => {
                return item.id === u[0]
            })
            UserUtils.setModifiedJSONFromArray(target,u)
        }

        const addUserSuccessCallback = u => {
            users.value.push(UserUtils.getShowJSONFromArray(u))
        }

        return {
            //data
            users, loading, noneUser, noneTipText, showModifyDrawer, showAddDrawer,

            //components
            modifyDrawer,

            //methods
            addUser, modifyUser, deleteUser, modifyUserSuccessCallback, addUserSuccessCallback
        }
    }
})
</script>

<style scoped>

</style>