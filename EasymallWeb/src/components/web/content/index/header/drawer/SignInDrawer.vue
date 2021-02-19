<template>
    <el-drawer ref="drawer" direction="rtl" size="20%" title="用户登录">
        <Form ref="form" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem label="用户名" prefix-icon="el-icon-user"></FormItem>
            <FormItem password></FormItem>
            <FormItem verification-code></FormItem>
        </Form>
        <el-button type="primary" @click="signIn">
            <i class="el-icon-arrow-right"></i>登录<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import axios from "axios";
import URL from "../../../../../../js/constant/URL";
import Utils from "../../../../../../js/utils/Utils";
import UserUtils from "../../../../../../js/utils/UserUtils";
import {defineComponent, ref} from 'vue'
import {ElMessage} from 'element-plus'
import Form from "../../../../../utils/Form.vue";
import FormItem from "../../../../../utils/FormItem.vue";
import {useStore} from 'vuex'

export default defineComponent({
    name: "SignInDrawer",
    components: {FormItem, Form},
    setup(props, context) {
        const drawer = ref(null)
        const form = ref(null)
        const verificationCodeImage = ref('')
        const loadingVerificationCodeImage = ref(true)
        const store = useStore()

        const signIn = _ => {
            form.value.validate((valid) => {
                if (valid) {
                    const data = form.value.get()
                    const signInJSON = UserUtils.getSignInJSONFromArray(data)
                    axios.post(URL.userSignIn, signInJSON).then(res => {
                        if (Utils.responseCodeEquals(res, 100100)) {
                            ElMessage.success('登录成功')
                            const responseData = Utils.getResponseData(res)
                            let user = {
                                username: signInJSON.username,
                                password: signInJSON.password,
                                email: responseData.email,
                                nickname: responseData.nickname,
                            }
                            store.commit('signIn', {
                                user: user,
                                userToken: res.data.data.token
                            })
                            context.emit('success')
                        } else if (Utils.responseCodeEquals(res, 100101)) {
                            ElMessage.error('登录失败，用户名或密码错误')
                        } else if (Utils.responseCodeEquals(res, 100102)) {
                            ElMessage.error('验证码错误')
                        }
                    })
                } else {
                    ElMessage.error('请输入合法信息再进行登录')
                }
            })
        }

        return {
            //data
            verificationCodeImage, loadingVerificationCodeImage,

            //components
            form, drawer,

            //methods
            signIn
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>