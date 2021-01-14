<template>
    <el-empty v-if="noneProduct" :description="noneTipText"></el-empty>
    <el-row v-else>
        <el-col :span="24">
            <el-table v-loading="loadingProductTableData" element-loading-text="拼命加载中" :data="products">
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
                                       @click="modifyProduct(scope.row)">
                            </el-button>
                        </el-tooltip>
                        <el-popconfirm cancelButtonText='否' confirmButtonText='是' icon="el-icon-error" iconColor="red"
                            title="确定删除该商品吗？" @confirm="deleteProduct(scope.row)">
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
                    <el-tooltip content="添加商品" effect="light" placement="left">
                        <el-button circle icon="el-icon-plus" size="medium" type="primary"
                                   @click="addProduct"></el-button>
                    </el-tooltip>
                </el-col>
            </el-row>
        </el-col>
    </el-row>
    <AdminProductModifyDrawer ref="modifyDrawer" v-model="showModifyDrawer" @success="modifyProductSuccessCallback"></AdminProductModifyDrawer>
    <AdminProductAddDrawer v-model="showAddDrawer" @success="addProductSuccessCallback"></AdminProductAddDrawer>
</template>

<script>
import AdminProductModifyDrawer from "./AdminProductModifyDrawer.vue";
import axios from "axios";
import URL from "../../../../../js/constant/URL";
import AdminProductAddDrawer from "./AdminProductAddDrawer.vue";
import {defineComponent,ref} from 'vue'
import Utils from "../../../../../js/utils/Utils";
import {ElMessage} from "element-plus";
import ProductsUtils from "../../../../../js/utils/ProductsUtils";

export default defineComponent({
    name: "AdminProductManagementTabPane",
    components: {AdminProductAddDrawer, AdminProductModifyDrawer},
    setup() {
        const noneProduct = ref(false)
        const noneTipText = ref('商品列表为空')
        const loadingProductTableData = ref(true)
        const products = ref([])
        const modifyDrawer = ref(null)
        const showModifyDrawer = ref(false)
        const showAddDrawer = ref(false)

        axios.get(URL.productGetAll).then(res => {
            if (Utils.responseCodeEquals(res, 110100)) {
                products.value = Utils.getResponseData(res)
                loadingProductTableData.value = false
            } else {
                noneProduct.value = true
                if (Utils.responseCodeNotEquals(res, 110101)) {
                    noneTipText.value = '未知原因加载失败'
                }
            }
        })

        const modifyProduct = row => {
            modifyDrawer.value.init(row)
            showModifyDrawer.value = true
        }

        const addProduct = _ =>{
            showAddDrawer.value = true
        }

        const deleteProduct = row => {
            axios.delete(URL.productDelete + row.id).then(res => {
                if (Utils.responseCodeEquals(res, 110300)) {
                    products.value.forEach((item, i) => {
                        if (item.id === row.id) {
                            products.value.splice(i, 1)
                        }
                    })
                    ElMessage.success('删除成功')
                }
            })
        }

        const modifyProductSuccessCallback = p => {
            const target = products.value.find(item => {
                return item.id === p[0]
            })
            ProductsUtils.setModifiedJSONFromArray(target,p)
        }

        const addProductSuccessCallback = p => {
            products.value.push(ProductsUtils.getShowJSONFromArray(p))
        }

        return {
            //data
            noneProduct,noneTipText,loadingProductTableData,products,showModifyDrawer,showAddDrawer,

            //components
            modifyDrawer,

            //methods
            modifyProduct,addProduct,deleteProduct,modifyProductSuccessCallback,addProductSuccessCallback,
        }
    }
})
</script>

<style scoped>

</style>