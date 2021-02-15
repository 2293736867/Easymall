<template>
    <el-drawer destroy-on-close ref="drawer" direction="rtl" size="25%" title="用户添加">
        <Form ref="form" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem ref="username" :check-function="usernameCheck" label="用户名" prefix-icon="el-icon-user"></FormItem>
            <FormItem label="昵称" prefix-icon="el-icon-news"></FormItem>
            <FormItem :check-function="emailCheck" label="电子邮箱" prefix-icon="el-icon-message"></FormItem>
            <FormItem password></FormItem>
        </Form>
        <el-button type="primary" @click="commit">
            <i class="el-icon-arrow-right"></i>添加<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import axios from "axios"
import URL from "../../../../../js/constant/URL";
import Utils from "../../../../../js/utils/Utils";
import Form from "../../../../utils/Form.vue";
import FormItem from "../../../../utils/FormItem.vue";
import {ElMessage} from 'element-plus'
import {defineComponent, ref} from 'vue'
import UserUtils from "../../../../../js/utils/UserUtils";

export default defineComponent({
    name: "AdminUserAddDrawer",
    components: {FormItem, Form},
    setup(props, context) {
        const form = ref(null)
        const username = ref(null)
        const drawer = ref(null)

        const usernameCheck = Validation.usernameCheck
        const emailCheck = Validation.emailCheck

        const commit = _ => {
            form.value.validate((valid) => {
                if (valid) {
                    const data = form.value.get()
                    axios.post(URL.userAdd, UserUtils.getAddJSONFromArray(data)).then(res => {
                        if (Utils.responseCodeEquals(res, 100002)) {
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
            form, username,drawer,

            //methods
            usernameCheck, emailCheck, commit
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>