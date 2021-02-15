<template>
    <el-drawer ref="drawer" destroy-on-close direction="rtl" size="50%" title="注册">
        <Form ref="form" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem :check-function="usernameCheck" label="用户名" prefix-icon="el-icon-user"></FormItem>
            <FormItem :check-function="emailCheck" label="邮箱" prefix-icon="el-icon-message"></FormItem>
            <FormItem password></FormItem>
<!--            <FormItem verification-code></FormItem>-->
        </Form>
        <el-button type="primary" @click="signUp">
            <i class="el-icon-arrow-right"></i>注册<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import axios from "axios";
import URL from "../../../../../../js/constant/URL";
import UserUtils from "../../../../../../js/utils/UserUtils";
import Utils from "../../../../../../js/utils/Utils";
import Form from "../../../../../utils/Form.vue";
import FormItem from "../../../../../utils/FormItem.vue";
import {defineComponent, ref} from 'vue'
import {ElNotification, ElMessage} from 'element-plus'
import Validation from "../../../../../../js/utils/Validation";

export default defineComponent({
    name: "SignUpDrawer",
    components: {FormItem, Form},
    setup(props, context) {
        const usernameCheck = Validation.usernameCheck
        const emailCheck = Validation.emailCheck
        const form = ref(null)
        const drawer = ref(null)

        const signUp = _ => {
            form.value.validate(valid => {
                if (valid) {
                    axios.post(URL.userSignUp, UserUtils.getSignUpJSONFromArray(form.value.get())).then(res => {
                        console.log(res)
                        if (Utils.responseCodeEquals(res, 100000)) {
                            ElNotification.success({
                                title: '注册成功',
                                message: '即将跳转到登录',
                                position: 'top-left'
                            })
                            context.emit('success')
                            drawer.value.handleClose()
                        } else if (Utils.responseCodeEquals(res, 100001)) {
                            ElMessage.error('验证码错误')
                        }
                    })
                } else {
                    ElMessage.error('请输入合法信息再进行注册')
                }
            })
        }

        return {
            //components
            form, drawer,

            //methods
            usernameCheck, emailCheck, signUp,
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>