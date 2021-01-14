<template>
    <el-drawer destroy-on-close ref="drawer" direction="rtl" size="25%" title="用户添加">
        <Form ref="form" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem ref="username" :check-function="usernameCheck" label="用户名" prefix-icon="el-icon-user"></FormItem>
            <FormItem label="昵称" prefix-icon="el-icon-news"></FormItem>
            <FormItem :check-function="emailCheck" label="电子邮箱" prefix-icon="el-icon-message"></FormItem>
            <FormItem :check-function="passwordCheck" label="密码" password
                      prefix-icon="el-icon-s-cooperation"></FormItem>
        </Form>
        <el-button type="primary" @click="commit">
            <i class="el-icon-arrow-right"></i>添加<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import REG from "../../../../../js/constant/REG";
import axios from "axios"
import URL from "../../../../../js/constant/URL";
import Utils from "../../../../../js/utils/Utils";
import Form from "../../../../utils/Form.vue";
import FormItem from "../../../../utils/FormItem.vue";
import {ElNotification, ElMessage} from 'element-plus'
import {defineComponent, ref} from 'vue'
import UserUtils from "../../../../../js/utils/UserUtils";

export default defineComponent({
    name: "AdminUserAddDrawer",
    components: {FormItem, Form},
    setup(props, context) {
        const form = ref(null)
        const username = ref(null)
        const drawer = ref(null)

        const usernameCheck = value => {
            return new Promise(((resolve, reject) => {
                if (!value) {
                    reject('请输入用户名')
                } else if (!REG.username.test(value)) {
                    reject('用户名非法')
                } else {
                    axios.get(URL.userCheckUsername + username.value.get()).then(res => {
                        if (Utils.responseCodeEquals(res, 100104)) {
                            reject('用户名已存在')
                        } else {
                            resolve()
                        }
                    })
                }
            }))
        }

        const passwordCheck = value => {
            let result = false
            let message = ''
            if (!value) {
                message = '请输入密码'
            } else if (!REG.password.test(value)) {
                ElNotification.warning({
                    title: '密码最少6位',
                    message: '最少1数字+1特殊字符+1大写+1小写',
                    position: 'top-left'
                })
                message = '密码过于简单'
            } else {
                result = true
            }
            return Utils.validatedResult(result,message)
        }

        const emailCheck = value => {
            let result = false
            let message = ''
            if (!value) {
                message = '请输入邮箱'
            } else if (!REG.email.test(value)) {
                message = '请输入合法的邮箱'
            } else {
                result = true
            }
            return Utils.validatedResult(result,message)
        }

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
            usernameCheck, emailCheck, passwordCheck, commit
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>