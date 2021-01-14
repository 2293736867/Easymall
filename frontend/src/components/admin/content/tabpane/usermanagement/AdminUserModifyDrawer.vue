<template>
    <el-drawer destroy-on-close ref="drawer" direction="rtl" size="25%" title="用户编辑">
        <Form ref="form" v-loading="loading" style="margin-left: 2.5rem;margin-right: 2.5rem">
            <FormItem disabled label="id" no-check prefix-icon="el-icon-s-flag"></FormItem>
            <FormItem ref="username" label="用户名" prefix-icon="el-icon-user" :check-function="usernameCheck"></FormItem>
            <FormItem label="昵称" prefix-icon="el-icon-news"></FormItem>
            <FormItem label="邮箱" prefix-icon="el-icon-message" :check-function="emailCheck"></FormItem>
        </Form>
        <el-button type="primary" @click="commit">
            <i class="el-icon-arrow-right"></i>修改<i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import REG from "../../../../../js/constant/REG";
import axios from "axios"
import URL from "../../../../../js/constant/URL";
import Form from "../../../../utils/Form.vue";
import FormItem from "../../../../utils/FormItem.vue";
import {ElMessage} from 'element-plus'
import {defineComponent, ref} from 'vue'
import Utils from "../../../../../js/utils/Utils";
import UserUtils from "../../../../../js/utils/UserUtils";

export default defineComponent({
    name: "AdminUserModifyDrawer",
    components: {FormItem, Form},
    setup(props, context) {
        const drawer = ref(null)
        const user = ref('')
        const form = ref(null)
        const loading = ref(true)
        const username = ref(null)
        let oldUsername = ''

        const usernameCheck = value => {
            let result = false
            let message = ''
            if (!value) {
                message = '请输入用户名'
            } else if (!REG.username.test(value)) {
                message = '用户名非法'
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

        const init = user => {
            loading.value = true
            oldUsername = user.username
            setTimeout(() => {
                form.value.set(UserUtils.getPreModifyArrayFromJSON(user))
                loading.value = false
            }, 500)
        }

        const commit = _ => {
            if(form.value.ifChanged()) {

                form.value.validate((valid) => {
                    if (valid) {
                        const tempName = username.value.get()
                        if (tempName !== oldUsername) {
                            axios.get(URL.userCheckUsername + tempName).then(res => {
                                if (Utils.responseCodeEquals(res, 100104)) {
                                    ElMessage.warning('用户名已存在')
                                } else {
                                    userUpdate()
                                }
                            })
                        } else {
                            userUpdate()
                        }
                    } else {
                        ElMessage.warning('请输入合法信息')
                    }
                })
            }else{
                ElMessage.info('未修改信息')
            }
        }

        const userUpdate = _ => {
            const data = form.value.get()
            axios.put(URL.userUpdate, UserUtils.getModifyJSONFromArray(data)).then(res => {
                if (Utils.responseCodeEquals(res, 100200)) {
                    ElMessage.success('修改成功')
                    context.emit('success', data)
                    drawer.value.handleClose()
                } else {
                    ElMessage.warning('未知原因修改失败')
                }
            })
        }

        return {
            //data
            user, loading,

            //components
            form, drawer, username,

            //methods
            usernameCheck, emailCheck, init, commit
        }
    },
    emits: ['success']
})
</script>

<style scoped>

</style>