<template>
    <el-drawer ref="drawer" destroy-on-close direction="rtl" size="25%" title="添加商品">
        <Form ref="form" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem label="名称" prefix-icon="el-icon-connection"></FormItem>
            <FormItem label="价格" prefix-icon="el-icon-price-tag" num-check>
                <template #append>元</template>
            </FormItem>
            <FormItem label="评分" prefix-icon="el-icon-medal" max-num="5.0">
                <template #append>0.0-5.0</template>
            </FormItem>
            <FormItem label="运费" prefix-icon="el-icon-mobile" num-check>
                <template #append>元</template>
            </FormItem>
            <FormItem label="分类" prefix-icon="el-icon-reading"></FormItem>
            <FormItem label="库存" prefix-icon="el-icon-files" num-check></FormItem>
            <FormItem label="描述" prefix-icon="el-icon-collection-tag" no-check></FormItem>
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

export default defineComponent({
    name: "AdminProductAddDrawer",
    components: {Form, FormItem},
    setup() {
        const drawer = ref(null)
        const form = ref(null)

        const commit = _ => {
            form.value.valid(valid=>{
                if(valid){
                    console.log(form.value.get())
                }
            })
            // elForm.value.validate((valid) => {
            //     if(valid){
            //         axios.post(URL.productAdd,{
            //             name:form.value.name,
            //             price:form.value.price,
            //             rating:form.value.rating,
            //             freight:form.value.freight,
            //             category:form.value.category,
            //             num:form.value.num,
            //             description:form.value.description
            //         }).then(res=>{
            //             if(parseInt(res.data.code) === 110000){
            //                 ElMessage.success('添加成功')
            //                 form.value.id = res.data.data
            //                 context.emit('success',form.value)
            //                 elDrawer.value.handleClose()
            //             }else{
            //                 ElMessage.error('未知原因添加失败')
            //             }
            //         })
            //     }else{
            //         ElMessage.warning('请输入合法信息')
            //     }
            // })
        }

        return {
            form, drawer,

            commit,
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>