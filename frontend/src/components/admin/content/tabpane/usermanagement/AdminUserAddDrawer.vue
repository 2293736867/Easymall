<template>
    <el-drawer ref="drawer" direction="rtl" size="25%" title="用户添加">
        <el-form ref="form" :model="form" :rules="rules" status-icon style="margin-left: 2.5rem;margin-right: 2.5rem;">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" clearable prefix-icon="el-icon-user">
                </el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
                <el-input v-model="form.nickname" clearable prefix-icon="el-icon-news"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" clearable prefix-icon="el-icon-s-cooperation" show-password
                          type="password"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
                <el-input v-model="form.email" clearable prefix-icon="el-icon-message"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="commit">
            <i class="el-icon-arrow-right"></i>
            添加
            <i class="el-icon-arrow-left"></i>
        </el-button>
    </el-drawer>
</template>

<script>
import REG from "../../../../../js/constant/REG";
import axios from "axios"
import URL from "../../../../../js/constant/URL";
import Utils from "../../../../../js/utils/Utils";

export default {
    name: "AdminUserAddDrawer",
    data() {
        let usernameCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入用户名'))
            if (!REG.username.test(value))
                callback(new Error('用户名非法'))
            axios.get(URL.userCheckUsername + this.form.username).then(res => {
                if (parseInt(res.data.code) === 100104) {
                    callback(new Error('用户名已存在'))
                } else {
                    callback()
                }
            })
        }

        let nicknameCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入昵称'))
            }
            callback()
        }

        let passwordCheck = (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入密码'))
            }
            if (!REG.password.test(value)) {
                this.$notify({
                    title: '密码最少6位',
                    message: '最少1数字+1特殊字符+1大写+1小写',
                    type: 'warning',
                    position: 'top-left'
                })
                callback(new Error('密码过于简单'))
            }
            callback()
        }

        let emailCheck = (rule, value, callback) => {
            if (!value)
                callback(new Error('请输入邮箱'))
            if (!REG.email.test(value))
                callback(new Error('请输入合法的邮箱'))
            callback()
        }

        return {
            form: {
                id:'',
                username: '',
                nickname: '',
                email: '',
                password:'',
            },
            rules: {
                username: [{validator: usernameCheck, trigger: 'blur'}],
                password:[{validator:passwordCheck,trigger:'blur'}],
                nickname: [{validator: nicknameCheck, trigger: 'blur'}],
                email: [{validator: emailCheck, trigger: 'blur'}],
            },
        }
    },
    methods: {
        commit() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post(URL.userAdd, {
                        username: this.form.username,
                        nickname:this.form.nickname,
                        password: Utils.sha3(this.form.password),
                        email: this.form.email,
                    }).then(res => {
                        if (parseInt(res.data.code) === 100002) {
                            this.$message.success('添加成功')
                            this.form.id = res.data.data
                            this.$emit('success',this.form)
                            this.$refs.drawer.handleClose()
                        } else {
                            this.$message.error('未知原因添加失败')
                        }
                    })
                } else {
                    this.$message.warning('请输入合法信息')
                }
            })
        },
    },
    emits: ['success']
}
</script>

<style scoped>

</style>