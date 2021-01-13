<template>
    <el-drawer ref="drawer" destroy-on-close direction="rtl" size="25%" title="商品编辑">
        <Form ref="form" v-loading="loading" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem label="id" prefix-icon="el-icon-s-flag"></FormItem>
            <FormItem label="名称" prefix-icon="el-icon-connection"></FormItem>
            <FormItem label="价格" num-check prefix-icon="el-icon-price-tag">
                <template #append>元</template>
            </FormItem>
            <FormItem label="评分" max-num="5.0" prefix-icon="el-icon-medal">
                <template #append>0.0-5.0</template>
            </FormItem>
            <FormItem label="运费" num-check prefix-icon="el-icon-mobile">
                <template #append>元</template>
            </FormItem>
            <FormItem label="分类" prefix-icon="el-icon-reading"></FormItem>
            <FormItem label="库存" num-check prefix-icon="el-icon-files"></FormItem>
            <FormItem label="描述" no-check prefix-icon="el-icon-collection-tag"></FormItem>
        </Form>
        <el-button style="margin-top: 2rem" type="primary" @click="commit">
            <i class="el-icon-arrow-right"></i>修改<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import axios from "axios"
import URL from "../../../../../js/constant/URL";
import Form from "../../../../utils/Form.vue";
import FormItem from "../../../../utils/FormItem.vue";
import {defineComponent,ref} from 'vue'
import {ElMessage} from "element-plus";
import ProductsUtils from "../../../../../js/utils/ProductsUtils";
import Utils from "../../../../../js/utils/Utils";

export default defineComponent({
    name: "AdminProductModifyDrawer",
    components: {FormItem, Form},
    setup(props, context) {
        const drawer = ref(null)
        const form = ref(null)
        const loading = ref(true)

        const init = p=>{
            loading.value = true
            setTimeout(()=>{
                form.value.set(ProductsUtils.getPreModifyArrayFromJSON(p))
                loading.value = false
            },500)
        }

        const commit = _ => {
            form.value.validate(valid => {
                if (valid) {
                    const data = form.value.get()
                    console.log(data)
                    axios.put(URL.productUpdate + data[0], ProductsUtils.getModifyJSONFromArray(data)).then(res => {
                        if (Utils.responseCodeEquals(res, 110200)) {
                            ElMessage.success('修改成功')
                            context.emit('success', data)
                            drawer.value.handleClose()
                        } else {
                            ElMessage.error('未知原因修改失败')
                        }
                    })
                } else {
                    ElMessage.warning('请输入合法信息')
                }
            })
        }

        return {
            //components
            form, drawer,loading,

            //methods
            init,commit
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>