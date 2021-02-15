<template>
    <el-row>
        <el-col>
            <el-tabs type="border-card">
                <el-tab-pane>
                    <template #label>
                        <span><i class="el-icon-house"></i>管理面板首页</span>
                    </template>
                    <AdminHomeTabPane></AdminHomeTabPane>
                </el-tab-pane>
                <el-tab-pane>
                    <template #label>
                        <span><i class="el-icon-user"></i>用户管理</span>
                    </template>
                    <AdminUserManagementTabPane></AdminUserManagementTabPane>
                </el-tab-pane>
                <el-tab-pane>
                    <template #label>
                        <span><i class="el-icon-goods"></i>商品管理</span>
                    </template>
                    <AdminProductManagementTabPane></AdminProductManagementTabPane>
                </el-tab-pane>
            </el-tabs>
        </el-col>
    </el-row>
</template>

<script>
import AdminHomeTabPane from "./tabpane/AdminHomeTabPane.vue";
import AdminUserManagementTabPane from "./tabpane/usermanagement/AdminUserManagementTabPane.vue";
import AdminProductManagementTabPane from "./tabpane/productManagement/AdminProductManagementTabPane.vue";
import {defineComponent} from 'vue'

export default defineComponent({
    name: "AdminIndex",
    components: {AdminProductManagementTabPane, AdminUserManagementTabPane, AdminHomeTabPane},
})
</script>

<style scoped>

</style>