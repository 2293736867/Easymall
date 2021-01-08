<template>
    <el-empty v-if="none" :description="noneText"></el-empty>
    <el-row v-else>
        <el-col :span="24">

            <el-table v-loading="loadingTable" :data="products">
                <el-table-column fixed label="Id" prop="id" width="300"></el-table-column>
                <el-table-column label="名称" prop="name" width="300"></el-table-column>
                <el-table-column label="价格" prop="price" width="300"></el-table-column>
                <el-table-column label="评分" prop="rating" width="300"></el-table-column>
                <el-table-column label="运费" prop="freight" width="300"></el-table-column>
                <el-table-column label="分类" prop="category" width="300"></el-table-column>
                <el-table-column label="库存" prop="num" width="300"></el-table-column>
                <el-table-column label="描述" prop="description" width="1200"></el-table-column>
                <el-table-column fixed="right" label="操作" width="200">
                    <template #default="scope">
                        <el-tooltip content="编辑" effect="light" placement="top">
                            <el-button circle icon="el-icon-edit" size="medium" type="primary"
                                       @click="editProduct(scope.row)">
                            </el-button>
                        </el-tooltip>
                        <el-popconfirm
                            cancelButtonText='否'
                            confirmButtonText='是'
                            icon="el-icon-error"
                            iconColor="red"
                            title="确定删除该商品吗？"
                            @confirm="deleteProduct(scope.row)"
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
                    <el-tooltip placement="left" content="添加商品" effect="light">
                        <el-button type="primary" circle icon="el-icon-plus" size="medium" @click="showAddDrawer"></el-button>
                    </el-tooltip>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
    <AdminProductModifyDrawer ref="modifyDrawer" v-model="modifyDrawer"
                              @success="modifyTable"></AdminProductModifyDrawer>
    <AdminProductAddDrawer v-model="addDrawer" @success="addTable"></AdminProductAddDrawer>
</template>

<script>
import AdminProductModifyDrawer from "./AdminProductModifyDrawer.vue";
import axios from "axios";
import URL from "../../../../../js/constant/URL";
import AdminProductAddDrawer from "./AdminProductAddDrawer.vue";

export default {
    name: "AdminProductManagementTabPane",
    components: {AdminProductAddDrawer, AdminProductModifyDrawer},
    data() {
        return {
            products: [],
            modifyDrawer: false,
            addDrawer: false,
            loadingTable: true,
            none: false,
            noneText: '商品列表为空',
        }
    },
    methods: {
        editProduct(row) {
            this.$refs.modifyDrawer.init(row)
            this.modifyDrawer = true
        },
        deleteProduct(row) {
            axios.delete(URL.productDelete+row.id).then(res=>{
                let code = parseInt(res.data.code)
                if(code === 110300){
                    this.products.forEach((item, i) => {
                        if (item.id === row.id)
                            this.products.splice(i, 1)
                    })
                    this.$message.success('删除成功')
                }
            })
        },
        modifyTable(p) {
            let target = this.products.find(item => {
                return item.id === p.id
            })
            target.name = p.name
            target.rating = p.rating
            target.freight = p.freight
            target.category = p.category
            target.description = p.description
            target.num = p.num
        },
        addTable(p) {
            this.products.push(p)
        },
        showAddDrawer(){
            this.addDrawer = true
        }
    },
    mounted() {
        axios.get(URL.productGetAll).then(res => {
            let code = parseInt(res.data.code)
            if (code === 110100) {
                this.products = res.data.data
                this.loadingTable = false
            } else {
                this.none = true
                if (code !== 110101) {
                    this.none = '未知原因加载失败'
                }
            }
        })
    },
}
</script>

<style scoped>

</style>