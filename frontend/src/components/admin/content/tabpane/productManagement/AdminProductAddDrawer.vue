<template>
    <el-drawer ref="drawer" destroy-on-close direction="rtl" size="25%" title="添加商品">
        <Form ref="form" style="margin-left: 2.5rem;margin-right: 2.5rem">
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
            <i class="el-icon-arrow-right"></i>添加<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import axios from "axios"
import URL from "../../../../../js/constant/URL";
import {defineComponent, ref} from 'vue'
import FormItem from "../../../../utils/FormItem.vue";
import Form from "../../../../utils/Form.vue";
import {ElMessage} from 'element-plus'
import Utils from "../../../../../js/utils/Utils";
import ProductsUtils from "../../../../../js/utils/ProductsUtils";

export default defineComponent({
    name: "AdminProductAddDrawer",
    components: {Form, FormItem},
    setup(props,context) {
        const drawer = ref(null)
        const form = ref(null)

        const commit = _ => {
            form.value.validate(valid => {
                if (valid) {
                    const data = form.value.get()
                    axios.post(URL.productAdd, ProductsUtils.getAddJSONFromArray(data)).then(res => {
                        if (Utils.responseCodeEquals(res,110000)){
                            ElMessage.success('添加成功')
                            data.unshift(Utils.getResponseData(res))
                            context.emit('success', data)
                            drawer.value.handleClose()
                        } else {
                            ElMessage.error('未知原因添加失败')
                        }
                    })
                } else {
                    ElMessage.warning('请输入合法信息')
                }
            })
        }

        return {
            //components
            form, drawer,

            //methods
            commit,
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>